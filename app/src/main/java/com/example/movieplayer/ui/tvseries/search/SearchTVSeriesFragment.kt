package com.example.movieplayer.ui.tvseries.search

import android.os.Bundle
import android.view.View
import androidx.activity.addCallback
import androidx.core.view.doOnPreDraw
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieplayer.MainActivity
import com.example.movieplayer.R
import com.example.movieplayer.databinding.SearchListBinding
import com.example.movieplayer.feature.fetchtvseries.data.TVSeries
import com.example.movieplayer.ui.common.updateState
import com.example.movieplayer.ui.tvseries.TVSeriesAdapter
import com.example.movieplayer.utils.hideKeyboard
import com.example.movieplayer.utils.showKeyboard
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchTVSeriesFragment : Fragment(R.layout.search_list) {

    private val viewModel: SearchTVSeriesViewModel by viewModels()

    companion object {
        const val DURATION = 300L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialElevationScale(false).apply {
            duration = DURATION
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = DURATION
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            (requireActivity() as MainActivity).searchEditText.setText("")
            findNavController().popBackStack()
        }

        val binding: SearchListBinding = SearchListBinding.bind(requireView())
        binding.lifecycleOwner = viewLifecycleOwner

        val activity = activity as? MainActivity
        val actionBar = activity?.supportActionBar
        actionBar?.title = null

        val tvSeriesAdapter = TVSeriesAdapter { itemView, tvSeries ->
            navigateToTvSeriesDetails(itemView, tvSeries)
        }
        binding.searchRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.searchRecyclerView.adapter = tvSeriesAdapter

        view.doOnPreDraw { startPostponedEnterTransition() }
        postponeEnterTransition()

        activity?.searchEditText?.apply {
            requestFocus()
            showKeyboard(this)
            doAfterTextChanged { keyword ->
                viewModel.onQueryChanged(keyword.toString())
            }
            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    hideKeyboard()
                }
            }
        }

        binding.searchRetryButton.setOnClickListener {
            tvSeriesAdapter.retry()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.querySearchResults.collectLatest { pagingData ->
                tvSeriesAdapter.submitData(pagingData)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            tvSeriesAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .collect { loadStates ->
                    binding.updateState(loadStates, tvSeriesAdapter)
                }
        }
    }

    private fun navigateToTvSeriesDetails(
        itemView: View,
        tvSeries: TVSeries
    ) {
        val extras = FragmentNavigatorExtras(
            itemView to getString(R.string.tv_card_detail_transition_name)
        )
        findNavController().navigate(
            SearchTVSeriesFragmentDirections.actionSearchTvSeriesFragmentToTVSeriesDetailsFragment(
                tvSeries
            ),
            extras
        )
    }
}

package com.beti1205.movieapp.ui.tvseries.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.compose.ui.platform.ComposeView
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.beti1205.movieapp.MainActivity
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries
import com.beti1205.movieapp.utils.hideKeyboard
import com.beti1205.movieapp.utils.showKeyboard
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchTVSeriesFragment : Fragment() {

    private val viewModel: SearchTVSeriesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            SearchTVSeriesScreen(
                viewModel = viewModel,
                onTVSeriesClicked = ::navigateToTvSeriesDetails
            )
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            (requireActivity() as MainActivity).searchEditText.setText("")
            findNavController().popBackStack()
        }

        val activity = activity as? MainActivity
        val actionBar = activity?.supportActionBar
        actionBar?.title = null

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
    }

    private fun navigateToTvSeriesDetails(tvSeries: TVSeries) {
        findNavController().navigate(
            SearchTVSeriesFragmentDirections.actionSearchTvSeriesFragmentToTVSeriesDetailsFragment(
                tvSeries
            )
        )
    }
}

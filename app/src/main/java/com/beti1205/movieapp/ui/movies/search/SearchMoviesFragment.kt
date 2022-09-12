package com.beti1205.movieapp.ui.movies.search

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
import com.beti1205.movieapp.MainActivity
import com.beti1205.movieapp.R
import com.beti1205.movieapp.databinding.SearchListBinding
import com.beti1205.movieapp.feature.fetchmovies.data.Movie
import com.beti1205.movieapp.ui.common.getErrorState
import com.beti1205.movieapp.ui.common.updateState
import com.beti1205.movieapp.ui.movies.MovieAdapter
import com.beti1205.movieapp.utils.hideKeyboard
import com.beti1205.movieapp.utils.showKeyboard
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChangedBy
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchMoviesFragment : Fragment(R.layout.search_list) {

    private val viewModel: SearchMoviesViewModel by viewModels()

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

        val movieAdapter = MovieAdapter { itemView, movie ->
            navigateToMovieDetails(itemView, movie)
        }
        binding.searchRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.searchRecyclerView.adapter = movieAdapter

        movieAdapter.addLoadStateListener { states ->
            if (states.getErrorState() != null) {
                finishTransition(view)
            }
        }
        movieAdapter.addOnPagesUpdatedListener {
            finishTransition(view)
        }
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
            movieAdapter.retry()
        }

        viewLifecycleOwner.lifecycleScope.launch {
            movieAdapter.loadStateFlow
                .distinctUntilChangedBy { it.refresh }
                .collect { loadStates ->
                    binding.updateState(loadStates, movieAdapter)
                }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.querySearchResults.collectLatest { pagingData ->
                movieAdapter.submitData(pagingData)
            }
        }
    }

    private fun finishTransition(view: View) {
        view.doOnPreDraw { startPostponedEnterTransition() }
    }

    private fun navigateToMovieDetails(
        itemView: View,
        movie: Movie
    ) {
        val extras = FragmentNavigatorExtras(
            itemView to getString(R.string.movie_card_detail_transition_name)
        )
        findNavController().navigate(
            SearchMoviesFragmentDirections.actionSearchMoviesFragmentToMovieDetailsFragment(
                movie
            ),
            extras
        )
    }
}

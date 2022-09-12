package com.example.movieplayer.ui.movies

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieplayer.R
import com.example.movieplayer.databinding.MovieListBinding
import com.example.movieplayer.feature.fetchmovies.data.Movie
import com.example.movieplayer.feature.fetchmovies.domain.MovieOrder
import com.example.movieplayer.ui.common.Preferences
import com.example.movieplayer.ui.common.getErrorState
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.movie_list) {

    @Inject
    lateinit var preferences: Preferences

    private val viewModel: MovieViewModel by hiltNavGraphViewModels(R.id.moviesGraph)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding: MovieListBinding = MovieListBinding.bind(requireView())
        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        postponeEnterTransition()

        val movieAdapter = MovieAdapter { itemView, movie ->
            navigateToMovieDetails(itemView, movie)
        }

        binding.movieRecyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.movieRecyclerView.adapter = movieAdapter

        movieAdapter.addLoadStateListener { states ->
            if (states.getErrorState() != null) {
                finishTransition(view)
            }
        }
        movieAdapter.addOnPagesUpdatedListener {
            finishTransition(view)
        }

        val savedOrder = restoreOrder()
        val selectedChipId = getSelectedChipId(savedOrder)
        binding.chipGroup.check(selectedChipId)
        viewModel.onOrderChanged(savedOrder)

        binding.chipGroup.setOnCheckedStateChangeListener { _, checkedIds ->
            onOrderChanged(checkedIds)
        }

        binding.retryButton.setOnClickListener {
            movieAdapter.retry()
        }

        addMenuProvider()

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movies.collectLatest { pagingData ->
                movieAdapter.submitData(pagingData)
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            movieAdapter.loadStateFlow.collectLatest { loadStates ->
                binding.errorView.isVisible = loadStates.getErrorState() != null
            }
        }
    }

    private fun addMenuProvider() {
        activity?.addMenuProvider(
            MovieMenuProvider {
                findNavController().navigate(
                    MovieFragmentDirections.actionMovieFragmentToSearchMoviesFragment()
                )
            },
            viewLifecycleOwner,
            Lifecycle.State.STARTED
        )
    }

    private fun onOrderChanged(checkedIds: List<Int>) {
        val order = when (checkedIds.first()) {
            R.id.topRated -> MovieOrder.TOP_RATED
            R.id.upcoming -> MovieOrder.UPCOMING
            R.id.nowPlaying -> MovieOrder.NOW_PLAYING
            else -> MovieOrder.POPULAR
        }

        viewModel.onOrderChanged(order)
        saveOrder(order)
    }

    private fun getSelectedChipId(savedOrder: MovieOrder) =
        when (savedOrder) {
            MovieOrder.TOP_RATED -> R.id.topRated
            MovieOrder.UPCOMING -> R.id.upcoming
            MovieOrder.NOW_PLAYING -> R.id.nowPlaying
            else -> R.id.popular
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
            MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(
                movie
            ),
            extras
        )
    }

    private fun saveOrder(order: MovieOrder) {
        preferences.movieOrder = order.ordinal
    }

    private fun restoreOrder(): MovieOrder = MovieOrder.from(preferences.movieOrder)
}

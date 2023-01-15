package com.beti1205.movieapp.ui.movies.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.fetchmovies.data.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by hiltNavGraphViewModels(R.id.moviesGraph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MovieScreen(
                viewModel = viewModel,
                onMovieClicked = ::navigateToMovieDetails,
                onSearchClicked = ::navigateToSearchMovies
            )
        }
    }

    private fun navigateToMovieDetails(movie: Movie) {
        findNavController().navigate(
            MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(movie.id)
        )
    }

    private fun navigateToSearchMovies() {
        findNavController().navigate(
            MovieFragmentDirections.actionMovieFragmentToSearchMoviesFragment()
        )
    }
}

package com.beti1205.movieapp.ui.movies.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.beti1205.movieapp.feature.fetchmovies.data.Movie
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchMoviesFragment : Fragment() {

    private val viewModel: SearchMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            SearchMoviesScreen(
                viewModel = viewModel,
                onMovieClicked = ::navigateToMovieDetails,
                onBackPressed = ::onBackPressed
            )
        }
    }

    private fun navigateToMovieDetails(movie: Movie) {
        findNavController().navigate(
            SearchMoviesFragmentDirections.actionSearchMoviesFragmentToMovieDetailsFragment(
                movie.id
            )
        )
    }

    private fun onBackPressed() {
        findNavController().popBackStack()
    }
}

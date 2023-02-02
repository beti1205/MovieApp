/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MovieDetailsScreen(
                viewModel = viewModel,
                onPersonClicked = ::navigateToPersonDetails,
                onButtonClicked = ::navigateToMovieReviews,
                onBackPressed = ::onBackPressed
            )
        }
    }
    private fun onBackPressed() {
        findNavController().popBackStack()
    }
    private fun navigateToPersonDetails(id: Int) {
        findNavController().navigate(
            MovieDetailsFragmentDirections.actionMovieDetailsFragmentToPersonDetailsFragment(id)
        )
    }

    private fun navigateToMovieReviews(id: Int) {
        findNavController().navigate(
            MovieDetailsFragmentDirections.actionMovieDetailsFragmentToMovieReviewsFragment(id)
        )
    }
}

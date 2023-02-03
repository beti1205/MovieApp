/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account

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
class AccountFragment : Fragment() {

    private val viewModel: AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AccountScreen(
                viewModel = viewModel,
                onMovieClicked = ::navigateToMovieDetails,
                onTVSeriesClicked = ::navigateToTVSeriesDetails
            )
        }
    }

    override fun onResume() {
        super.onResume()

        viewModel.fetchFavoriteMovies()
        viewModel.fetchFavoriteTVSeries()
    }

    private fun navigateToMovieDetails(movieId: Int) {
        findNavController().navigate(
            AccountFragmentDirections.actionAccountFragmentToMovieDetailsFragment(movieId)
        )
    }

    private fun navigateToTVSeriesDetails(tvSeriesId: Int) {
        findNavController().navigate(
            AccountFragmentDirections.actionAccountFragmentToTVSeriesDetailsFragment(tvSeriesId)
        )
    }
}

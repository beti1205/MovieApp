/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.persondetails

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
class PersonDetailsFragment : Fragment() {

    private val viewModel: PersonDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            PersonDetailsScreen(
                viewModel = viewModel,
                onMovieClicked = ::navigateToMovieDetails,
                onTVSeriesClicked = ::navigateToTVSeriesDetails,
                onBackPressed = ::onBackPressed
            )
        }
    }

    private fun onBackPressed() {
        findNavController().popBackStack()
    }
    private fun navigateToMovieDetails(id: Int) {
        findNavController().navigate(
            PersonDetailsFragmentDirections.actionPersonDetailsFragmentToMovieDetailsFragment(id)
        )
    }

    private fun navigateToTVSeriesDetails(id: Int) {
        findNavController().navigate(
            PersonDetailsFragmentDirections.actionPersonDetailsFragmentToTVSeriesDetailsFragment(id)
        )
    }
}

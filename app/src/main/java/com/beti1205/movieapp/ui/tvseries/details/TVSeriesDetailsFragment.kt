/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.details

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
class TVSeriesDetailsFragment : Fragment() {

    private val viewModel: TVSeriesDetailsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            TVSeriesDetailsScreen(
                viewModel = viewModel,
                onPersonClicked = ::navigateToPersonDetails,
                onBackPressed = ::onBackPressed
            )
        }
    }

    private fun onBackPressed() {
        findNavController().popBackStack()
    }

    private fun navigateToPersonDetails(id: Int) {
        findNavController().navigate(
            TVSeriesDetailsFragmentDirections.actionTVSeriesDetailsFragmentToPersonDetailsFragment(
                id
            )
        )
    }
}

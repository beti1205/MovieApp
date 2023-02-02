/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.search

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
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
                onTVSeriesClicked = ::navigateToTvSeriesDetails,
                onBackPressed = ::onBackPressed
            )
        }
    }

    private fun onBackPressed() {
        findNavController().popBackStack()
    }

    private fun navigateToTvSeriesDetails(tvSeries: TVSeries) {
        findNavController().navigate(
            SearchTVSeriesFragmentDirections.actionSearchTvSeriesFragmentToTVSeriesDetailsFragment(
                tvSeries.id
            )
        )
    }
}

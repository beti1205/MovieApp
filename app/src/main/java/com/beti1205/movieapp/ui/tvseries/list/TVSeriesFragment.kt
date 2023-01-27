package com.beti1205.movieapp.ui.tvseries.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.hilt.navigation.fragment.hiltNavGraphViewModels
import androidx.navigation.fragment.findNavController
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVSeriesFragment : Fragment() {

    private val viewModel: TVSeriesViewModel by hiltNavGraphViewModels(R.id.tvGraph)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            TVSeriesScreen(
                viewModel = viewModel,
                onTVSeriesClicked = ::navigateToTvSeriesDetails,
                onSearchClicked = ::navigateToSearchTVSeries
            )
        }
    }

    private fun navigateToTvSeriesDetails(tvSeries: TVSeries) {
        findNavController().navigate(
            TVSeriesFragmentDirections.actionTVSeriesFragmentToTVSeriesDetailsFragment(tvSeries.id)
        )
    }

    private fun navigateToSearchTVSeries() {
        findNavController().navigate(
            TVSeriesFragmentDirections.actionTVSeriesFragmentToSearchTvSeriesFragment()
        )
    }
}

package com.beti1205.movieapp.ui.tvseries.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVSeriesDetailsFragment : Fragment() {

    private val viewModel: TVSeriesDetailsViewModel by viewModels()
    private val args: TVSeriesDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            TVSeriesDetailsScreen(viewModel = viewModel)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val tvSeries = args.selectedTVSeries
        viewModel.fetchSeasons(tvSeries.id)
    }
}

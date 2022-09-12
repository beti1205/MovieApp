package com.beti1205.movieapp.ui.tvseries.details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.beti1205.movieapp.R
import com.beti1205.movieapp.databinding.TvseriesDetailsBinding
import com.beti1205.movieapp.utils.themeColor
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVSeriesDetailsFragment : Fragment(R.layout.tvseries_details) {

    private val viewModel: TVSeriesDetailsViewModel by viewModels()
    private val args: TVSeriesDetailsFragmentArgs by navArgs()

    companion object {
        const val DURATION = 300L
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.myNavHostFragment
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
            duration = DURATION
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding: TvseriesDetailsBinding = TvseriesDetailsBinding.bind(requireView())

        val tvSeries = args.selectedTVSeries

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.episodesRecyclerView.adapter = EpisodeAdapter()

        viewModel.fetchSeasons(tvSeries.id)
    }
}

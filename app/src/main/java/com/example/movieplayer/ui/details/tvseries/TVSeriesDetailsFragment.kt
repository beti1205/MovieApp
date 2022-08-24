package com.example.movieplayer.ui.details.tvseries

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieplayer.R
import com.example.movieplayer.databinding.TvseriesDetailsBinding
import com.example.movieplayer.utils.themeColor
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TVSeriesDetailsFragment : Fragment(R.layout.tvseries_details) {

    private val viewModel: TVSeriesDetailsViewModel by viewModels()
    private val args: TVSeriesDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.myNavHostFragment
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))
            duration = 300
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding: TvseriesDetailsBinding = TvseriesDetailsBinding.bind(requireView())

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        val tvSeries = args.selectedTVSeries
        viewModel.fetchSeasons(tvSeries.id)
    }
}

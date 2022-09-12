package com.beti1205.movieapp.ui.movies.details

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.beti1205.movieapp.R
import com.beti1205.movieapp.databinding.MovieDetailsBinding
import com.beti1205.movieapp.utils.themeColor
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.movie_details) {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

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

        val binding: MovieDetailsBinding = MovieDetailsBinding.bind(requireView())
        val movie = args.selectedMovie

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.castRecyclerView.adapter = CastAdapter()
        binding.crewRecyclerView.adapter = CrewAdapter()

        viewModel.fetchCredits(movie.id)
    }
}

package com.example.movieplayer.ui.details.movie

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieplayer.R
import com.example.movieplayer.databinding.MovieDetailsBinding
import com.example.movieplayer.utils.themeColor
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment(R.layout.movie_details) {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

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

        val binding: MovieDetailsBinding = MovieDetailsBinding.bind(requireView())
        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner
        binding.castRecyclerView.adapter = CastAdapter()
        binding.crewRecyclerView.adapter = CrewAdapter()

        val movie = args.selectedMovie
        viewModel.fetchCredits(movie.id)
    }
}

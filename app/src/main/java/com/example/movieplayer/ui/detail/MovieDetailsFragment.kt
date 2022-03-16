package com.example.movieplayer.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.*
import android.view.animation.DecelerateInterpolator
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieplayer.R
import com.example.movieplayer.databinding.MovieDetailBinding
import com.google.android.material.transition.MaterialContainerTransform

class MovieDetailsFragment : Fragment() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.myNavHostFragment
            scrimColor = Color.TRANSPARENT
            interpolator = DecelerateInterpolator()
            duration = 500
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: MovieDetailBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.movie_detail,
            container,
            false
        )

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        binding.lifecycleOwner = this

        val args: MovieDetailsFragmentArgs by navArgs()
        val movie = args.selectedMovie
        val viewModelFactory = MovieDetailsViewModelFactory(movie, application)

        val viewModel: MovieDetailsViewModel by viewModels { viewModelFactory }
        binding.viewModel = viewModel

        val transitionName = "movie${movie.id}"
        binding.cardView.transitionName = transitionName

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        postponeEnterTransition()
//        view.doOnPreDraw { startPostponedEnterTransition() }
    }

}
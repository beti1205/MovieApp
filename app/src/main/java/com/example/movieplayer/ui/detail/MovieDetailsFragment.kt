package com.example.movieplayer.ui.detail

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import com.example.movieplayer.R
import com.example.movieplayer.databinding.ItemDetailsBinding
import com.google.android.material.transition.MaterialContainerTransform
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {

    private val viewModel: MovieDetailsViewModel by viewModels()
    private val args: MovieDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = MaterialContainerTransform().apply {
            drawingViewId = R.id.myNavHostFragment
            scrimColor = Color.TRANSPARENT
            interpolator = DecelerateInterpolator()
            duration = 300
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: ItemDetailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.item_details,
            container,
            false
        )

        binding.viewModel = viewModel

        @Suppress("UNUSED_VARIABLE")
        val application = requireNotNull(activity).application
        binding.lifecycleOwner = this

        val movie = args.selectedMovie
        val transitionName = "movie${movie.id}"
        binding.imageView.transitionName = transitionName

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        postponeEnterTransition()
//        view.doOnPreDraw { startPostponedEnterTransition() }
    }

}
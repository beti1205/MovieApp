package com.beti1205.movieapp.ui.movies.reviews

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
class MovieReviewsFragment : Fragment() {

    private val viewModel: MovieReviewsViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            MovieReviewsScreen(
                viewModel = viewModel,
                onBackPressed = ::onBackPressed
            )
        }
    }

    private fun onBackPressed() {
        findNavController().popBackStack()
    }
}

package com.beti1205.movieapp.ui.account

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
class AccountFragment : Fragment() {

    private val viewModel: AccountViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(requireContext()).apply {
        setContent {
            AccountScreen(
                viewModel = viewModel,
                onMovieClicked = ::navigateToMovieDetails
            )
        }
    }

    private fun navigateToMovieDetails(movieId: Int) {
        findNavController().navigate(
            AccountFragmentDirections.actionAccountFragmentToMovieDetailsFragment(movieId)
        )
    }
}

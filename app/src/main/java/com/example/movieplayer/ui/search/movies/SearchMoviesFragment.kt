package com.example.movieplayer.ui.search.movies

import android.os.Bundle
import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieplayer.MainActivity
import com.example.movieplayer.R
import com.example.movieplayer.databinding.SearchListBinding
import com.example.movieplayer.ui.movies.MovieAdapter
import com.example.movieplayer.utils.hideKeyboard
import com.example.movieplayer.utils.showKeyboard
import com.google.android.material.transition.MaterialElevationScale
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchMoviesFragment : Fragment(R.layout.search_list) {

    private val viewModel: SearchMoviesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        exitTransition = MaterialElevationScale(false).apply {
            duration = 300
        }
        reenterTransition = MaterialElevationScale(true).apply {
            duration = 300
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding: SearchListBinding = SearchListBinding.bind(requireView())

        binding.lifecycleOwner = viewLifecycleOwner

        binding.searchRecyclerView.layoutManager = GridLayoutManager(context, 2)

        val movieAdapter = MovieAdapter { itemView, movie ->
            val extras = FragmentNavigatorExtras(
                itemView to getString(R.string.movie_card_detail_transition_name)
            )
            findNavController().navigate(
                SearchMoviesFragmentDirections.actionSearchMoviesFragmentToMovieDetailsFragment(
                    movie
                ),
                extras
            )
        }
        binding.searchRecyclerView.adapter = movieAdapter

        view.doOnPreDraw { startPostponedEnterTransition() }
        postponeEnterTransition()

        val activity = activity as? MainActivity
        val actionBar = activity?.supportActionBar
        actionBar?.title = null

        activity?.searchEditText?.apply {
            requestFocus()
            showKeyboard(this)
            doAfterTextChanged { keyword ->
                viewModel.onQueryChanged(keyword.toString())
            }

            setOnFocusChangeListener { _, hasFocus ->
                if (!hasFocus) {
                    hideKeyboard()
                }
            }
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.querySearchResults.collectLatest { pagingData ->
                movieAdapter.submitData(pagingData)
            }
        }
    }
}

package com.example.movieplayer.ui.movies

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.view.MenuProvider
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieplayer.R
import com.example.movieplayer.databinding.MovieListBinding
import com.example.movieplayer.feature.fetchmovies.domain.MovieOrder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MovieFragment : Fragment(R.layout.movie_list) {

    private val viewModel: MovieViewModel by viewModels()

    private val sharedPreferences: SharedPreferences? by lazy {
        activity?.getPreferences(Context.MODE_PRIVATE)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val binding: MovieListBinding = MovieListBinding.bind(requireView())

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        binding.movieRecyclerView.layoutManager = GridLayoutManager(context, 2)

        val movieAdapter = MovieAdapter { itemView, movie ->
            val extras = FragmentNavigatorExtras(
                itemView to getString(R.string.movie_card_detail_transition_name)
            )
            findNavController().navigate(
                MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(
                    movie
                ),
                extras
            )
        }
        binding.movieRecyclerView.adapter = movieAdapter

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.movies.collectLatest { pagingData ->
                movieAdapter.submitData(pagingData)
            }
        }

        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }

        val savedOrder = restoreOrder()
        val selectedChipId = when (savedOrder) {
            MovieOrder.TOP_RATED -> R.id.topRated
            MovieOrder.UPCOMING -> R.id.upcoming
            MovieOrder.NOW_PLAYING -> R.id.nowPlaying
            else -> R.id.popular
        }
        binding.chipGroup.check(selectedChipId)
        viewModel.onOrderChanged(savedOrder)

        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            val order = when (checkedId) {
                R.id.topRated -> MovieOrder.TOP_RATED
                R.id.upcoming -> MovieOrder.UPCOMING
                R.id.nowPlaying -> MovieOrder.NOW_PLAYING
                else -> MovieOrder.POPULAR
            }

            viewModel.onOrderChanged(order)
            saveOrder(order)
        }

        activity?.addMenuProvider(
            object : MenuProvider {
                override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                    menuInflater.inflate(R.menu.appbar_menu, menu)
                }

                override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                    when (menuItem.itemId) {
                        R.id.search_item -> findNavController().navigate(
                            MovieFragmentDirections.actionMovieFragmentToSearchMoviesFragment()
                        )
                    }
                    return true
                }
            },
            viewLifecycleOwner,
            Lifecycle.State.RESUMED
        )
        movieAdapter.addOnPagesUpdatedListener {
            view.doOnPreDraw { startPostponedEnterTransition() }
        }

        postponeEnterTransition()
    }

    private fun saveOrder(order: MovieOrder) {
        sharedPreferences?.edit {
            putInt(getString(R.string.saved_order_key), order.ordinal)
        }
    }

    private fun restoreOrder(): MovieOrder {
        val value =
            sharedPreferences?.getInt(
                getString(R.string.saved_order_key),
                MovieOrder.POPULAR.ordinal
            )
        return MovieOrder.from(value)
    }

    private fun onNetworkError() {
        if (!viewModel.isNetworkErrorShown.value!!) {
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}

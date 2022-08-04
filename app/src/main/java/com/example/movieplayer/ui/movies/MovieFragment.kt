package com.example.movieplayer.ui.movies

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.core.content.edit
import androidx.core.view.MenuProvider
import androidx.core.view.doOnPreDraw
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieplayer.MainActivity
import com.example.movieplayer.R
import com.example.movieplayer.databinding.MovieListBinding
import com.example.movieplayer.domain.MovieOrder
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by viewModels()

    private val sharedPreferences: SharedPreferences? by lazy {
        activity?.getPreferences(Context.MODE_PRIVATE)
    }
    private lateinit var binding: MovieListBinding

    private val mainActivity: MainActivity?
        get() {
            val activity = activity as? MainActivity
            return activity
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.movie_list,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        viewModel.navigateToSelectedMovie.observe(viewLifecycleOwner) { selectedMovie ->
            if (selectedMovie == null) {
                return@observe
            }

            val position = selectedMovie.position
            val viewHolder =
                binding.recyclerView.findViewHolderForLayoutPosition(position) as? MovieViewHolder
            viewHolder?.let {
//                exitTransition = MaterialElevationScale(false).apply {
//                    duration = 600
//                }
//                reenterTransition = MaterialElevationScale(false).apply {
//                    duration = 600
//                }

                val transitionName = "movie${selectedMovie.movie.id}"
                val extras = FragmentNavigatorExtras(
                    viewHolder.binding.item to transitionName
                )

                findNavController().navigate(
                    MovieFragmentDirections.actionMovieFragmentToMovieDetailsFragment(
                        selectedMovie.movie
                    ),
                    extras
                )
                viewModel.displayPropertyDetailsComplete()

            }
        }

        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = MovieAdapter { movie, position ->
            viewModel.displayMovieDetails(movie, position)
        }

        viewModel.eventNetworkError.observe(viewLifecycleOwner) { isNetworkError ->
            if (isNetworkError) onNetworkError()
        }

        val savedOrder = restoreOrder()
        val selectedChipId = when (savedOrder) {
            MovieOrder.TOP_RATED -> R.id.top_rated
            MovieOrder.UPCOMING -> R.id.upcoming
            MovieOrder.NOW_PLAYING -> R.id.now_playing
            else -> R.id.popular
        }
        binding.chipGroup.check(selectedChipId)
        viewModel.onOrderChanged(savedOrder)

        binding.chipGroup.setOnCheckedChangeListener { _, checkedId ->
            val order = when (checkedId) {
                R.id.top_rated -> MovieOrder.TOP_RATED
                R.id.upcoming -> MovieOrder.UPCOMING
                R.id.now_playing -> MovieOrder.NOW_PLAYING
                else -> MovieOrder.POPULAR
            }

            viewModel.onOrderChanged(order)
            saveOrder(order)
        }

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        postponeEnterTransition()
        view.doOnPreDraw {
            startPostponedEnterTransition()
        }
        mainActivity?.addMenuProvider(object: MenuProvider {
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
        }, viewLifecycleOwner)
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



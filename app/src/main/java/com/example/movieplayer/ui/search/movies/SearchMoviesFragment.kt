package com.example.movieplayer.ui.search.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
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
import com.example.movieplayer.ui.movies.MovieFragmentDirections
import com.example.movieplayer.ui.movies.MovieViewHolder
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchMoviesFragment : Fragment() {

    private val viewModel: SearchMoviesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: SearchListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.search_list,
            container,
            false
        )

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)

        val movieAdapter = MovieAdapter { movie, position ->
            viewModel.displayMovieDetails(movie, position)
        }
        binding.recyclerView.adapter = movieAdapter

        viewModel.navigateToSelectedMovie.observe(viewLifecycleOwner) { selectedMovie ->
            if (selectedMovie == null) {
                return@observe
            }

            val position = selectedMovie.position
            val viewHolder =
                binding.recyclerView.findViewHolderForLayoutPosition(position) as? MovieViewHolder
            viewHolder?.let {
                val transitionName = "movie${selectedMovie.movie.id}"
                val extras = FragmentNavigatorExtras(
                    viewHolder.binding.poster to transitionName
                )

                findNavController().navigate(
                    SearchMoviesFragmentDirections.actionSearchMoviesFragmentToMovieDetailsFragment(
                        selectedMovie.movie
                    ),
                    extras
                )
                viewModel.displayPropertyDetailsComplete()

            }
        }
        val activity = activity as? MainActivity
        val actionBar = activity?.supportActionBar
        actionBar?.title = null

        activity?.searchEditText?.doAfterTextChanged { keyword ->
            viewModel.onQueryChanged(keyword.toString())
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.querySearchResults.collectLatest { pagingData ->
                movieAdapter.submitData(pagingData)
            }
        }
        return binding.root
    }
}
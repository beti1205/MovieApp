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
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieplayer.MainActivity
import com.example.movieplayer.R
import com.example.movieplayer.databinding.SearchListBinding
import com.example.movieplayer.ui.movies.MovieAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

@AndroidEntryPoint
class SearchMoviesFragment : Fragment() {

    private val viewModel: SearchMoviesViewModel by viewModels()
    private var searchJob: Job? = null

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
        binding.recyclerView.adapter = MovieAdapter { movie, position ->
//            viewModel.displayMovieDetails(movie, position)
        }

        val activity = activity as? MainActivity
        val actionBar = activity?.supportActionBar
        actionBar?.title = null

        activity?.searchEditText?.doAfterTextChanged { keyword ->
            viewModel.onMovieNameChanged(keyword.toString())
//            searchJob?.cancel()
//
//            val query = keyword.toString()
//            if (query.length < 3) {
//                return@doAfterTextChanged
//            }
//
//            searchJob = lifecycleScope.launchWhenResumed {
//                delay(500)
//                viewModel.displayDataFromRepository(query)
//            }
        }
        return binding.root
    }
}
package com.example.movieplayer.ui.search.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.movieplayer.R
import com.example.movieplayer.databinding.SearchListBinding
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay

class SearchMoviesFragment : Fragment() {

    private val viewModel: SearchMoviesViewModel by viewModels {
        SearchMoviesViewModel.Factory(requireActivity().application)
    }

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

        binding.searchEditFrame.doAfterTextChanged { keyword ->
            searchJob?.cancel()

            val query = keyword.toString()
            if(query.length < 3) {
                return@doAfterTextChanged
            }

            searchJob = lifecycleScope.launchWhenResumed {
                delay(500)
                viewModel.displayDataFromRepository(query)
            }
        }

        return binding.root
    }
}
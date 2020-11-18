package com.example.movieplayer.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.movieplayer.R
import com.example.movieplayer.databinding.MovieListBinding

class MovieFragment : Fragment() {

//    private val viewModel2: MovieViewModel by lazy {
//        val activity = requireNotNull(this.activity) {
//
//        }
//        ViewModelProvider(this, MovieViewModel.Factory(activity.application))
//            .get(MovieViewModel::class.java)
//    }

    private val viewModel: MovieViewModel by viewModels {
        MovieViewModel.Factory(requireActivity().application)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val binding: MovieListBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.movie_list,
            container,
            false
        )

        val adapter = MovieAdapter()

        binding.lifecycleOwner = viewLifecycleOwner

        binding.viewModel = viewModel

        binding.recyclerView.layoutManager = GridLayoutManager(context, 2)
        binding.recyclerView.adapter = adapter

        viewModel.eventNetworkError.observe(viewLifecycleOwner, Observer<Boolean>{
            isNetworkError -> if( isNetworkError) onNetworkError()
        })

        return binding.root

    }

    private fun onNetworkError(){
        if(!viewModel.isNetworkErrorShown.value!!){
            Toast.makeText(activity, "Network Error", Toast.LENGTH_LONG).show()
            viewModel.onNetworkErrorShown()
        }
    }
}



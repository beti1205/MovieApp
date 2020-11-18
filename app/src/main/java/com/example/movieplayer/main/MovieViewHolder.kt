package com.example.movieplayer.main

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.movieplayer.databinding.MovieItemBinding
import com.example.movieplayer.domain.MovieModel
import com.example.movieplayer.network.Movie

class MovieViewHolder private constructor(
    val binding: MovieItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: MovieModel) {
        binding.movie = movie
        binding.executePendingBindings()
    }

    companion object {

        fun from(parent: ViewGroup): MovieViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = MovieItemBinding.inflate(layoutInflater, parent, false)
            return MovieViewHolder(binding)
        }
    }
}
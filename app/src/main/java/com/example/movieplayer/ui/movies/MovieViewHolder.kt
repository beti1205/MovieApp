package com.example.movieplayer.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieplayer.databinding.MovieItemBinding

class MovieViewHolder private constructor(
    val binding: MovieItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: com.example.movieplayer.domain.Movie) {
        val transitionName = "movie${movie.id}"
        binding.item.transitionName = transitionName
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
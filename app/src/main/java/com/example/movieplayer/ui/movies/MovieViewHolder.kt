package com.example.movieplayer.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.movieplayer.databinding.MovieItemBinding
import com.example.movieplayer.feature.fetchmovies.data.Movie

class MovieViewHolder private constructor(
    val binding: MovieItemBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(onClick: (View, Movie) -> Unit, item: Movie) {
        binding.onClick = onClick
        binding.movie = item
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

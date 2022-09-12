package com.beti1205.movieapp.ui.movies

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.beti1205.movieapp.databinding.MovieItemBinding
import com.beti1205.movieapp.feature.fetchmovies.data.Movie

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

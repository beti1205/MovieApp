package com.beti1205.movieapp.ui.movies

import androidx.recyclerview.widget.DiffUtil
import com.beti1205.movieapp.feature.fetchmovies.data.Movie

class MovieDiffCallback : DiffUtil.ItemCallback<Movie>() {
    override fun areItemsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Movie, newItem: Movie): Boolean {
        return oldItem == newItem
    }
}

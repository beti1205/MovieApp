package com.example.movieplayer.ui.movies

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.example.movieplayer.domain.Movie

class MovieAdapter(private val onClick: (movie: Movie, position: Int) -> Unit) :
    ListAdapter<com.example.movieplayer.domain.Movie, MovieViewHolder>(MovieDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val item = getItem(position)
        holder.itemView.setOnClickListener {
            onClick(item, position)
        }
        holder.bind(item)
    }
}

class MovieDiffCallback : DiffUtil.ItemCallback<com.example.movieplayer.domain.Movie>() {

    override fun areItemsTheSame(
        oldItem: com.example.movieplayer.domain.Movie,
        newItem: com.example.movieplayer.domain.Movie
    ): Boolean {
        return oldItem.id == newItem.id
    }


    override fun areContentsTheSame(
        oldItem: com.example.movieplayer.domain.Movie,
        newItem: com.example.movieplayer.domain.Movie
    ): Boolean {
        return oldItem == newItem
    }

}
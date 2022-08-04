package com.example.movieplayer

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieplayer.domain.Movie
import com.example.movieplayer.ui.movies.MovieAdapter

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Movie>?) {
    val adapter = recyclerView.adapter as MovieAdapter
    adapter.submitList(data)
}
@BindingAdapter("imageUrl")
fun setImageUrl(imageView: ImageView, url: String?) {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    val imageUri = "$baseUrl$url"
    imageView.load(imageUri) {
        crossfade(true)
        placeholder(R.drawable.placeholder_image)
        error(R.drawable.error_image)
    }
}


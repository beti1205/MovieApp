package com.example.movieplayer.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import coil.load
import com.example.movieplayer.R

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


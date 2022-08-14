package com.example.movieplayer.common

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.movieplayer.R
import com.example.movieplayer.feature.fetchcredits.data.Cast
import com.example.movieplayer.feature.fetchcredits.data.Crew
import com.example.movieplayer.ui.detail.CastAdapter
import com.example.movieplayer.ui.detail.CrewAdapter


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

@BindingAdapter("listCrewData")
fun bindCrewRecyclerView(recyclerView: RecyclerView, data: List<Crew>?) {
    val adapter = recyclerView.adapter as CrewAdapter
    adapter.submitList(data)
}

@BindingAdapter("listCastData")
fun bindCastRecyclerView(recyclerView: RecyclerView, data: List<Cast>?) {
    val adapter = recyclerView.adapter as CastAdapter
    adapter.submitList(data)
}

@BindingAdapter("android:visibility")
fun setVisibility(view: View, value: Boolean) {
    view.visibility = if (value) View.VISIBLE else View.GONE
}

package com.example.movieplayer.ui.movies.details

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieplayer.feature.fetchcredits.data.Cast
import com.example.movieplayer.feature.fetchcredits.data.Crew

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

package com.beti1205.movieapp.ui.movies.details

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.feature.fetchcredits.data.Crew

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
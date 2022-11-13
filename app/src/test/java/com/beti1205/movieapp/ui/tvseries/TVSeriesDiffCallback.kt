package com.beti1205.movieapp.ui.tvseries

import androidx.recyclerview.widget.DiffUtil
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries

class TVSeriesDiffCallback : DiffUtil.ItemCallback<TVSeries>() {
    override fun areItemsTheSame(oldItem: TVSeries, newItem: TVSeries): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TVSeries, newItem: TVSeries): Boolean {
        return oldItem == newItem
    }
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries

import androidx.recyclerview.widget.DiffUtil
import com.beti1205.movieapp.feature.tvseries.data.TVSeries

class TVSeriesDiffCallback : DiffUtil.ItemCallback<TVSeries>() {
    override fun areItemsTheSame(oldItem: TVSeries, newItem: TVSeries): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: TVSeries, newItem: TVSeries): Boolean {
        return oldItem == newItem
    }
}

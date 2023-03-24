/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvseries.domain

import com.beti1205.movieapp.common.data.ApiResponse
import com.beti1205.movieapp.feature.tvseries.data.TVSeries

fun ApiResponse<TVSeries>.transformTVSeriesPosterPath(imageUrl: String) =
    this.copy(
        items = this.items.map { tvSeries ->
            val poster = when {
                tvSeries.posterPath != null -> "${imageUrl}${tvSeries.posterPath}"
                else -> null
            }
            tvSeries.copy(
                posterPath = poster
            )
        }
    )

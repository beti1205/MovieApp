/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui

import com.beti1205.movieapp.common.data.ApiResponse
import com.beti1205.movieapp.feature.tvseries.data.TVSeries

object TVSeriesDataProvider {

    val tvSeries = TVSeries(
        popularity = 632.02,
        id = 80752,
        overview = "A virus has decimated humankind. Those who survived emerged blind",
        name = "See",
        firstAirDate = "2019-11-01",
        originalName = "See",
        voteAverage = 8.3,
        posterPath = "/lKDIhc9FQibDiBQ57n3ELfZCyZg.jpg"
    )

    val apiResponse = ApiResponse(
        page = 1,
        totalResults = 1,
        totalPages = 1,
        items = listOf(tvSeries)
    )
}

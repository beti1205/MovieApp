package com.beti1205.movieapp.ui.tvseries.common

import androidx.paging.PagingData
import com.beti1205.movieapp.feature.tvseries.data.TVSeries

object PagingTVSeriesPreviewDataProvider {

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

    val pagingData: PagingData<TVSeries> = PagingData.from(
        listOf(
            tvSeries
        )
    )
}

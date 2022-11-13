package com.beti1205.movieapp.ui.tvseries.common

import androidx.paging.PagingData
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Genre
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Season

object TVSeriesPreviewDataProvider {

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
    val genres = listOf(
        Genre(
            id = 18,
            name = "Drama"
        ),
        Genre(
            id = 20,
            name = "Crime"
        )
    )
    val pagingData: PagingData<TVSeries> = PagingData.from(
        listOf(
            tvSeries
        )
    )

    val seasonsList = listOf(
        Season(
            airDate = "2021-09-21",
            episodeCount = 10,
            id = 170644,
            name = "Limited Series",
            overview = "In prison, Jeff's newfound fame makes him a target.",
            posterPath = "/h7YlJ1Mhg6jCZiHToUiKqHdzMO9.jpg",
            seasonNumber = 1
        ),
        Season(
            airDate = "2022-09-21",
            episodeCount = 10,
            id = 170645,
            name = "Limited Series",
            overview = "In prison, Jeff's newfound fame makes him a target.",
            posterPath = "/h7YlJ1Mhg6jCZiHToUiKqHdzMO9.jpg",
            seasonNumber = 2
        )
    )

    val genresList = listOf(
        Genre(
            id = 18,
            name = "Drama"
        ),
        Genre(
            id = 22,
            name = "Crime"
        )
    )

    val episodesList = listOf(
        Episode(
            id = 1019694,
            name = "Mijo",
            overview = "As his troubles escalate to a boiling point, Jimmy finds himself in dire straits.",
            posterPath = "/8XFhyx4xFCY2nZPThgOUF42G4fI.jpg",
            episodeAirDate = "2015-02-09",
            episodeNumber = 1
        )
    )
}

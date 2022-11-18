package com.beti1205.movieapp.ui.tvseries.details

import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.feature.fetchtvepisodes.data.SeasonResponse
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Season
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.TVSeriesDetails

object TVSeriesDetailsDataProvider {

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

    val seasonResponse = SeasonResponse(
        episodesList
    )

    val genresList = listOf(
        Genre(
            id = 18,
            name = "Drama"
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

    val tvSeriesDetails = TVSeriesDetails(
        firstAirDate = "2022-09-21",
        genres = genresList,
        inProduction = false,
        lastAirDate = "2022-09-21",
        numberOfEpisodes = 10,
        numberOfSeasons = 1,
        seasons = seasonsList
    )
}

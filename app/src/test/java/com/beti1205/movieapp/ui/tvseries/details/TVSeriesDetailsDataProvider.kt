package com.beti1205.movieapp.ui.tvseries.details

import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.feature.fetchcredits.data.Credits
import com.beti1205.movieapp.feature.fetchcredits.data.Crew
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
        id = 80752,
        overview = "A virus has decimated humankind. Those who survived emerged blind",
        name = "See",
        firstAirDate = "2019-11-01",
        voteAverage = 8.3,
        posterPath = "/lKDIhc9FQibDiBQ57n3ELfZCyZg.jpg",
        genres = listOf(
            Genre(
                id = 18,
                name = "Drama"
            ),
            Genre(
                id = 20,
                name = "Crime"
            )
        ),
        inProduction = true,
        lastAirDate = "2019-11-01",
        numberOfEpisodes = 2,
        numberOfSeasons = 2,
        seasons = seasonsList
    )

    val cast = listOf(
        Cast(
            id = 1,
            name = "Grace Caroline Currey",
            popularity = 8.9,
            character = "Becky",
            path = "/6chZcnjWEiFfpmB6D5BR9YUeIs9.jpg"
        )
    )
    val crew = listOf(
        Crew(
            id = 90812,
            name = "Scott Mann",
            popularity = 7.356,
            job = "Director",
            department = "Directing",
            path = "/8WygpUzfdfztZQqxGE5zn3rCedJ.jpg"
        )
    )
    val credits = Credits(
        id = 1,
        cast = cast,
        crew = crew
    )
}

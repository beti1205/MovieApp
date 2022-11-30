package com.beti1205.movieapp.ui.persondetails

import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCast

object PersonDetailsPreviewDataProvider {

    val personDetails = PersonDetails(
        id = 31,
        birthday = "1956-07-09",
        deathday = null,
        name = "Tom Hanks",
        biography = "Thomas Jeffrey Hanks (born July 9, 1956) ...",
        popularity = 69.464,
        birthPlace = "Concord, California, USA",
        personPoster = null
    )

    val personMovieCastList = listOf<PersonMovieCast>(
        PersonMovieCast(
            character = "Himself",
            title = "Murch: Walter Murch on Editing",
            popularity = 1.0,
            id = 1,
            overview = "",
            voteCount = 100,
            voteAverage = 1.0,
            creditId = "1",
            releaseDate = "2007-01-26",
            genreIds = listOf(1),
            originalLanguage = "",
            originalTitle = "",
            posterPath = null
        )
    )
}

package com.beti1205.movieapp.ui.persondetails

import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCreditsResponse
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCrew

object PersonDetailsDataProvider {

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

    private val personMovieCastList = listOf(
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

    private val personMovieCrewList = listOf(
        PersonMovieCrew(
            id = 1,
            department = "Production",
            job = "Producer",
            overview = "",
            title = "Murch: Walter Murch on Editing",
            popularity = 1.0,
            originalTitle = "Murch: Walter Murch on Editing",
            originalLanguage = "",
            voteCount = 100,
            vote_average = 1.0,
            posterPath = "",
            genreIds = listOf(1),
            releaseDate = "2007-01-26",
            creditId = "1"
        )

    )

    val personMovieCreditsResponse = PersonMovieCreditsResponse(
        cast = personMovieCastList,
        crew = personMovieCrewList,
        id = 1
    )

    private val sectionMovieCast = Section.MovieCast(
        personMovieCastList,
        false
    )

    private val sectionMovieCrew = Section.MovieCrew(
        personMovieCrewList,
        false
    )

    val sectionsEmptyCredits = listOf(
        Section.MovieCast(emptyList(), false),
        Section.MovieCrew(emptyList(), false)
    )

    val sectionsList = listOf(
        sectionMovieCast,
        sectionMovieCrew
    )
}

package com.beti1205.movieapp.ui.persondetails

import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCreditsResponse
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCast
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCreditsResponse
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCrew

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

    private val personTVSeriesCastList = listOf(
        PersonTVSeriesCast(
            backdropPath = "/xXw353pKdfJ8ikYzH4FHAErCHNK.jpg",
            genreIds = emptyList(),
            id = 27023,
            originCountry = emptyList(),
            originalLanguage = "en",
            originalName = "The Oscars",
            overview = "An annual American awards ceremony honoring cinematic achievements in the film industry. The various category winners are awarded a copy of a statuette, officially the Academy Award of Merit, that is better known by its nickname Oscar.",
            popularity = 14.916,
            poster = "/wyMHJMQp8WpmBg9CxefvbQnFhrm.jpg",
            firstAirDate = "1953-03-18",
            name = "The Oscars",
            voteAverage = 6.9,
            voteCount = 55,
            character = "Self",
            creditId = "52588359760ee3466140cf9e",
            episodeCount = 1
        )
    )

    private val personTVSeriesCrewList = listOf(
        PersonTVSeriesCrew(
            backdropPath = "/xXw353pKdfJ8ikYzH4FHAErCHNK.jpg",
            genreIds = emptyList(),
            id = 27023,
            originCountry = emptyList(),
            originalLanguage = "en",
            originalName = "The Oscars",
            overview = "An annual American awards ceremony honoring cinematic achievements in the film industry. The various category winners are awarded a copy of a statuette, officially the Academy Award of Merit, that is better known by its nickname Oscar.",
            popularity = 14.916,
            posterPath = "/wyMHJMQp8WpmBg9CxefvbQnFhrm.jpg",
            firstAirDate = "1953-03-18",
            name = "The Oscars",
            voteAverage = 6.9,
            voteCount = 55,
            creditId = "52588359760ee3466140cf9e",
            episodeCount = 1,
            department = "Production",
            job = "Producer"
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
            voteAverage = 1.0,
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

    val personTVSeriesCreditsResponse = PersonTVSeriesCreditsResponse(
        cast = personTVSeriesCastList,
        crew = personTVSeriesCrewList,
        id = 1
    )

    val sectionMovieCast = Section(
        items = personMovieCastList,
        expanded = false,
        expandable = false
    )

    val sectionMovieCrew = Section(
        items = personMovieCrewList,
        expanded = false,
        expandable = false
    )

    val sectionTVCast = Section(
        items = personTVSeriesCastList,
        expanded = false,
        expandable = false
    )

    val sectionTVCrew = Section(
        items = personTVSeriesCrewList,
        expanded = false,
        expandable = false
    )

    val sectionEmptyMovieCast =
        Section<PersonMovieCast>(items = emptyList(), expanded = false, expandable = false)

    val sectionEmptyMovieCrew = Section<PersonMovieCrew>(
        items = emptyList(),
        expanded = false,
        expandable = false
    )
    val sectionEmptyTVCast = Section<PersonTVSeriesCast>(
        items = emptyList(),
        expanded = false,
        expandable = false
    )
    val sectionEmptyTVCrew = Section<PersonTVSeriesCrew>(
        items = emptyList(),
        expanded = false,
        expandable = false
    )
}

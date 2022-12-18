package com.beti1205.movieapp.ui.persondetails

import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCast
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCrew

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

    val personMovieCast = PersonMovieCast(
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

    val sectionMovieCast = Section(
        items = listOf(personMovieCast),
        expanded = false,
        expandable = false
    )

    private val personTVCast = PersonTVSeriesCast(
        character = "Himself",
        name = "Murch: Walter Murch on Editing",
        popularity = 1.0,
        id = 1,
        overview = "",
        voteCount = 100,
        voteAverage = 1.0,
        creditId = "1",
        firstAirDate = "2007-01-26",
        genreIds = listOf(1),
        originalLanguage = "",
        originalName = "",
        poster = null,
        episodeCount = 1,
        backdropPath = null,
        originCountry = listOf("en")
    )

    val sectionTVSeriesCast = Section(
        items = listOf(personTVCast),
        expanded = false,
        expandable = false
    )

    private val personMovieCrew = PersonMovieCrew(
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

    val sectionMovieCrew = Section(
        items = listOf(personMovieCrew),
        expanded = false,
        expandable = false
    )

    private val personTVCrew = PersonTVSeriesCrew(
        id = 1,
        department = "Production",
        job = "Producer",
        overview = "",
        name = "Murch: Walter Murch on Editing",
        popularity = 1.0,
        originalName = "Murch: Walter Murch on Editing",
        originalLanguage = "",
        voteCount = 100,
        voteAverage = 1.0,
        posterPath = "",
        genreIds = listOf(1),
        firstAirDate = "2007-01-26",
        creditId = "1",
        episodeCount = 1,
        backdropPath = null,
        originCountry = listOf("en")
    )

    val sectionTVCrew = Section(
        items = listOf(personTVCrew),
        expanded = false,
        expandable = false
    )
}

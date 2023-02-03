/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.persondetails

import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCast
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCrew

object SectionDataProvider {

    val personMovieCast = PersonMovieCast(
        character = "Self",
        title = "Through the Eyes of Forrest Gump",
        popularity = 3.526,
        id = 237353,
        overview = "A look behind the scenes of Robert Zemeckis' 1994 Oscar-winning film, 'Forrest Gump'.",
        voteCount = 17,
        voteAverage = 7.8,
        creditId = "52fe4e69c3a36847f828ea91",
        releaseDate = "1994-10-01",
        genreIds = listOf(99),
        originalLanguage = "en",
        originalTitle = "Through the Eyes of Forrest Gump",
        posterPath = "/dfjLE1HjdR9XhEpN04elCGUOJfA.jpg"
    )

    val personTVCast = PersonTVSeriesCast(
        character = "General George Meade",
        name = "1883",
        popularity = 106.327,
        id = 118357,
        overview = "Follow the Dutton family as they embark on a journey west through the " +
            "Great Plains toward the last bastion of untamed America. A stark retelling of " +
            "Western expansion, and an intense study of one family fleeing poverty to seek a" +
            " better future in America’s promised land — Montana.",
        voteCount = 232,
        voteAverage = 8.448,
        creditId = "61bf16a1d2f5b5001caade5b",
        firstAirDate = "2021-12-19",
        genreIds = listOf(37, 18),
        originalLanguage = "en",
        originalName = "1883",
        poster = "/qduRygYeQc50UIxN3tNwtvwvqj5.jpg",
        episodeCount = 1,
        backdropPath = null,
        originCountry = listOf("US")
    )

    val personMovieCrew = PersonMovieCrew(
        id = 937278,
        department = "Production",
        job = "Producer",
        overview = "The story of Otto Anderson, a grumpy widower who is very set in his ways.",
        title = "A Man Called Otto",
        popularity = 51.991,
        originalTitle = "A Man Called Otto",
        originalLanguage = "en",
        voteCount = 0,
        voteAverage = 0.0,
        posterPath = "/lltnxfKikNH91poAR2BTi8f1UGj.jpg",
        genreIds = listOf(18, 35),
        releaseDate = "2022-12-28",
        creditId = "1"
    )

    val personTVCrew = PersonTVSeriesCrew(
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
}

package com.beti1205.movieapp.ui.persondetails

import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCrew

sealed class Section {

    abstract val expanded: Boolean

    data class MovieCast(
        val cast: List<PersonMovieCast>,
        override val expanded: Boolean
    ) : Section()

    data class MovieCrew(
        val crew: List<PersonMovieCrew>,
        override val expanded: Boolean
    ) : Section()
}

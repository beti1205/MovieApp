package com.beti1205.movieapp.ui.persondetails

import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCast
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCrew

sealed class Section {

    abstract val expanded: Boolean
    abstract val expandable: Boolean

    data class MovieCast(
        val cast: List<PersonMovieCast>,
        override val expanded: Boolean,
        override val expandable: Boolean
    ) : Section()

    data class TVCast(
        val cast: List<PersonTVSeriesCast>,
        override val expanded: Boolean,
        override val expandable: Boolean
    ) : Section()

    data class MovieCrew(
        val crew: List<PersonMovieCrew>,
        override val expanded: Boolean,
        override val expandable: Boolean
    ) : Section()

    data class TVCrew(
        val crew: List<PersonTVSeriesCrew>,
        override val expanded: Boolean,
        override val expandable: Boolean
    ) : Section()
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.persondetails

import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCast
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCrew

data class PersonDetailsSection(
    val movieCast: Section<PersonMovieCast> = Section(),
    val movieCrew: Section<PersonMovieCrew> = Section(),
    val tvCast: Section<PersonTVSeriesCast> = Section(),
    val tvCrew: Section<PersonTVSeriesCrew> = Section()
)

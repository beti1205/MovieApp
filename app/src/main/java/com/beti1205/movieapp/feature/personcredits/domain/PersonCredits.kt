/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.personcredits.domain

import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCast
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCrew

data class PersonCredits(
    val personMovieCast: List<PersonMovieCast> = emptyList(),
    val personMovieCrew: List<PersonMovieCrew> = emptyList(),
    val personTVSeriesCast: List<PersonTVSeriesCast> = emptyList(),
    val personTVSeriesCrew: List<PersonTVSeriesCrew> = emptyList()
)

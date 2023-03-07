/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.personmoviecredits.data

import kotlinx.serialization.Serializable

@Serializable
data class PersonMovieCreditsResponse(
    val cast: List<PersonMovieCast>,
    val crew: List<PersonMovieCrew>,
    val id: Int
)

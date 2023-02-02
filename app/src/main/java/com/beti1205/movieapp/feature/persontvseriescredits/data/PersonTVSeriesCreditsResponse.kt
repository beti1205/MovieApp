/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.persontvseriescredits.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonTVSeriesCreditsResponse(
    val cast: List<PersonTVSeriesCast>,
    val crew: List<PersonTVSeriesCrew>,
    val id: Int
)

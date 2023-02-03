/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.persondetails.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonDetails(
    val id: Int,
    val birthday: String?,
    val deathday: String?,
    val name: String,
    val biography: String,
    val popularity: Double,

    @Json(name = "place_of_birth")
    val birthPlace: String?,

    @Json(name = "profile_path")
    val personPoster: String?
)

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.credits.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Credits(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)

@JsonClass(generateAdapter = true)
data class Cast(
    val id: Int,
    val name: String,
    val popularity: Double,
    val character: String,

    @Json(name = "profile_path")
    val path: String?
)

@JsonClass(generateAdapter = true)
data class Crew(
    val id: Int,
    val name: String,
    val popularity: Double,
    val job: String,

    @Json(name = "known_for_department")
    val department: String,

    @Json(name = "profile_path")
    val path: String?
)

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.credits.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Credits(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)

@Serializable
data class Cast(
    val id: Int,
    val name: String,
    val popularity: Double,
    val character: String,

    @SerialName("profile_path")
    val path: String?
)

@Serializable
data class Crew(
    val id: Int,
    val name: String,
    val popularity: Double,
    val job: String,

    @SerialName("known_for_department")
    val department: String,

    @SerialName("profile_path")
    val path: String?
)

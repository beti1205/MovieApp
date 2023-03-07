/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.persondetails.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonDetails(
    val id: Int,
    val birthday: String?,
    val deathday: String?,
    val name: String,
    val biography: String,
    val popularity: Double,

    @SerialName("place_of_birth")
    val birthPlace: String?,

    @SerialName("profile_path")
    val personPoster: String?
)

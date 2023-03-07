/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.accountdetails.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AccountDetails(
    val id: Int,
    val name: String?,
    val username: String,
    val avatar: Avatar
)

@Serializable
data class Avatar(
    val tmdb: Tmdb
)

@Serializable
data class Tmdb(
    @SerialName("avatar_path")
    val avatarPath: String?
)

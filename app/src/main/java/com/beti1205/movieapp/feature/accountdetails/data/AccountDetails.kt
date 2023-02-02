/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.accountdetails.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountDetails(
    val id: Int,
    val name: String?,
    val username: String,
    val avatar: Avatar
)

@JsonClass(generateAdapter = true)
data class Avatar(
    val tmdb: Tmdb
)

@JsonClass(generateAdapter = true)
data class Tmdb(
    @Json(name = "avatar_path")
    val avatarPath: String?
)

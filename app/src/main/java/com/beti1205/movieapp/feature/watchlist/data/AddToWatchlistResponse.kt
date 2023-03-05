/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.watchlist.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddToWatchlistResponse(
    @Json(name = "status_code")
    val statusCode: Int,

    @Json(name = "status_message")
    val statusMessage: String
)

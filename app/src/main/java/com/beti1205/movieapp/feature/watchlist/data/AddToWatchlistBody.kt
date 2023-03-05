/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.watchlist.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AddToWatchlistBody(
    val watchlist: Boolean,

    @Json(name = "media_type")
    val mediaType: String,

    @Json(name = "media_id")
    val mediaId: Int
)

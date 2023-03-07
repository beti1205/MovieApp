/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.watchlist.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AddToWatchlistBody(
    val watchlist: Boolean,

    @SerialName("media_type")
    val mediaType: String,

    @SerialName("media_id")
    val mediaId: Int
)

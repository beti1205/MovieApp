/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.favorite.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarkFavoriteBody(
    val favorite: Boolean,

    @SerialName("media_type")
    val mediaType: String,

    @SerialName("media_id")
    val mediaId: Int
)

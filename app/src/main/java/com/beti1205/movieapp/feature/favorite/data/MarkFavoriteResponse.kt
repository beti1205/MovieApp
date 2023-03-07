/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.favorite.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MarkFavoriteResponse(
    @SerialName("status_code")
    val statusCode: Int,

    @SerialName("status_message")
    val statusMessage: String
)

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.token.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RequestTokenResponse(
    val success: Boolean,

    @SerialName("expires_at")
    val expiresAt: String,

    @SerialName("request_token")
    val requestToken: String
)

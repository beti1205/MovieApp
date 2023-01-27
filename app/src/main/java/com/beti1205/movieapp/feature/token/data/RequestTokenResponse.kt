package com.beti1205.movieapp.feature.token.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RequestTokenResponse(
    val success: Boolean,

    @Json(name = "expires_at")
    val expiresAt: String,

    @Json(name = "request_token")
    val requestToken: String
)

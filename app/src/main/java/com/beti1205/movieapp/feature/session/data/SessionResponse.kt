package com.beti1205.movieapp.feature.session.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SessionResponse(
    val success: Boolean,
    @Json(name = "session_id")
    val sessionId: String?
)

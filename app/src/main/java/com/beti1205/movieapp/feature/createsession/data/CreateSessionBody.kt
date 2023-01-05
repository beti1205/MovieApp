package com.beti1205.movieapp.feature.createsession.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CreateSessionBody(
    @Json(name = "request_token")
    val requestToken: String
)

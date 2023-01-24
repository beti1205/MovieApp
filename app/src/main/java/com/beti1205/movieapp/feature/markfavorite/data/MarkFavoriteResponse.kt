package com.beti1205.movieapp.feature.markfavorite.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarkFavoriteResponse(
    @Json(name = "status_code")
    val statusCode: Int,

    @Json(name = "status_message")
    val statusMessage: String
)

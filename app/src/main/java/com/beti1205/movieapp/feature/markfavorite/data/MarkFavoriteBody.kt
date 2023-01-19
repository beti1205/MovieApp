package com.beti1205.movieapp.feature.markfavorite.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MarkFavoriteBody(
    val favorite: Boolean,

    @Json(name = "media_type")
    val mediaType: String,

    @Json(name = "media_id")
    val mediaId: Int
)

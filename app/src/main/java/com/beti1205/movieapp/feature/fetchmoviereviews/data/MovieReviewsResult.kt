package com.beti1205.movieapp.feature.fetchmoviereviews.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieReviewsResult(
    val results: List<MovieReviews>
)

@JsonClass(generateAdapter = true)
data class MovieReviews(
    val author: String,
    val content: String,
    val id: String,

    @Json(name = "created_at")
    val createdAt: String,

    @Json(name = "updated_up")
    val updatedUp: String
)

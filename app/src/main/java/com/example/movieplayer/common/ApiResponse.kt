package com.example.movieplayer.common

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ApiResponse<T>(
        val page: Int?,

        @Json(name = "total_results")
        val totalResults: Int?,

        @Json(name = "total_pages")
        val totalPages: Int?,

        @Json(name = "results")
        val items: List<T>
)
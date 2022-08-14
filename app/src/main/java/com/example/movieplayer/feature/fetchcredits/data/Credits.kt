package com.example.movieplayer.feature.fetchcredits.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Credits(
    val id: Int,
    val cast: List<Cast>,
    val crew: List<Crew>
)

@JsonClass(generateAdapter = true)
data class Cast(
    val id: Int,
    val name: String,
    val popularity: Double,
    @Json(name = "profile_path")
    val path: String?,
    val character: String
)

@JsonClass(generateAdapter = true)
data class Crew(
    val id: Int,
    @Json(name = "known_for_department")
    val department: String,
    val name: String,
    val popularity: Double,
    @Json(name = "profile_path")
    val path: String?,
    val job: String
)

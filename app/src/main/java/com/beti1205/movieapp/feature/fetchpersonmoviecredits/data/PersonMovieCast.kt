package com.beti1205.movieapp.feature.fetchpersonmoviecredits.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonMovieCast(
    val character: String,
    val vote_count: Int,
    val vote_average: Double,
    val title: String,
    val popularity: Double,
    val id: Int,
    val overview: String,

    @Json(name = "credit_id")
    val creditId: String,

    @Json(name = "release_date")
    val releaseDate: String,

    @Json(name = "genre_ids")
    val genreIds: List<Int>,

    @Json(name = "original_language")
    val originalLanguage: String,

    @Json(name = "original_title")
    val originalTitle: String,

    @Json(name = "poster_path")
    val posterPath: String?
)
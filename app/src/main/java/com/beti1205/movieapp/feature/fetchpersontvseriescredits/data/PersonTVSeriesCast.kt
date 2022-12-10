package com.beti1205.movieapp.feature.fetchpersontvseriescredits.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonTVSeriesCast(
    val id: Int,
    val character: String,
    val name: String,
    val popularity: Double,
    val overview: String,

    @Json(name = "credit_id")
    val creditId: String,

    @Json(name = "original_name")
    val originalName: String,

    @Json(name = "genre_ids")
    val genreIds: List<Int>,

    @Json(name = "poster_path")
    val poster: String?,

    @Json(name = "vote_count")
    val voteCount: Int,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "episode_count")
    val episodeCount: Int,

    @Json(name = "original_language")
    val originalLanguage: String,

    @Json(name = "first_air_date")
    val firstAirDate: String,

    @Json(name = "backdrop_path")
    val backdropPath: String?,

    @Json(name = "origin_country")
    val originCountry: List<String>
)

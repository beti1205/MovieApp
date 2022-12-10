package com.beti1205.movieapp.feature.fetchpersontvseriescredits.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class PersonTVSeriesCrew(
    val id: Int,
    val department: String,
    val job: String,
    val overview: String,
    val name: String,
    val popularity: Double,

    @Json(name = "original_language")
    val originalLanguage: String,

    @Json(name = "episode_count")
    val episodeCount: Int?,

    @Json(name = "origin_country")
    val originCountry: List<String>,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "original_name")
    val originalName: String,

    @Json(name = "genre_ids")
    val genreIds: List<Int>,

    @Json(name = "first_air_date")
    val firstAirDate: String,

    @Json(name = "backdrop_path")
    val backdropPath: String?,

    @Json(name = "vote_count")
    val voteCount: Int,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "credit_id")
    val creditId: String
)

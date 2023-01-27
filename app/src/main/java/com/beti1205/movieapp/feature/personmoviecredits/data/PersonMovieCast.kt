package com.beti1205.movieapp.feature.personmoviecredits.data

import com.beti1205.movieapp.utils.formattedRating
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonMovieCast(
    val character: String?,
    val title: String,
    val popularity: Double,
    val id: Int,
    val overview: String,

    @Json(name = "vote_count")
    val voteCount: Int,

    @Json(name = "vote_average")
    val voteAverage: Double,

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
) {
    val votes: String
        get() = voteAverage.formattedRating
}

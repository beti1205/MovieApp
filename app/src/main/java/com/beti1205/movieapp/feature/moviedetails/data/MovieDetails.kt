package com.beti1205.movieapp.feature.moviedetails.data

import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.utils.formattedRating
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetails(
    val id: Int,
    val genres: List<Genre>,
    val title: String,
    val overview: String,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "release_date")
    val releaseDate: String?
) {
    val votes: String
        get() = voteAverage.formattedRating
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.personmoviecredits.data

import com.beti1205.movieapp.utils.formattedRating
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonMovieCrew(
    val id: Int,
    val department: String,
    val job: String,
    val overview: String,
    val title: String,
    val popularity: Double,

    @Json(name = "original_language")
    val originalLanguage: String,

    @Json(name = "original_title")
    val originalTitle: String,

    @Json(name = "vote_count")
    val voteCount: Int,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "genre_ids")
    val genreIds: List<Int>,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "release_date")
    val releaseDate: String,

    @Json(name = "credit_id")
    val creditId: String
) {
    val votes: String
        get() = voteAverage.formattedRating
}

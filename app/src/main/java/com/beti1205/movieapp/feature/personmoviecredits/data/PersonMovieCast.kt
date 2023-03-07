/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.personmoviecredits.data

import com.beti1205.movieapp.utils.formattedRating
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonMovieCast(
    val character: String?,
    val title: String,
    val popularity: Double,
    val id: Int,
    val overview: String,

    @SerialName("vote_count")
    val voteCount: Int,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("credit_id")
    val creditId: String,

    @SerialName("release_date")
    val releaseDate: String,

    @SerialName("genre_ids")
    val genreIds: List<Int>,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    @SerialName("poster_path")
    val posterPath: String?
) {
    val votes: String
        get() = voteAverage.formattedRating
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.personmoviecredits.data

import com.beti1205.movieapp.utils.formattedRating
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonMovieCrew(
    val id: Int,
    val department: String,
    val job: String,
    val overview: String,
    val title: String,
    val popularity: Double,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("original_title")
    val originalTitle: String,

    @SerialName("vote_count")
    val voteCount: Int,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("genre_ids")
    val genreIds: List<Int>,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("release_date")
    val releaseDate: String,

    @SerialName("credit_id")
    val creditId: String
) {
    val votes: String
        get() = voteAverage.formattedRating
}

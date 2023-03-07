/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.persontvseriescredits.data

import com.beti1205.movieapp.utils.formattedRating
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonTVSeriesCrew(
    val id: Int,
    val department: String,
    val job: String,
    val overview: String,
    val name: String,
    val popularity: Double,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("episode_count")
    val episodeCount: Int?,

    @SerialName("origin_country")
    val originCountry: List<String>,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("original_name")
    val originalName: String,

    @SerialName("genre_ids")
    val genreIds: List<Int>,

    @SerialName("first_air_date")
    val firstAirDate: String,

    @SerialName("backdrop_path")
    val backdropPath: String?,

    @SerialName("vote_count")
    val voteCount: Int,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("credit_id")
    val creditId: String
) {
    val votes: String
        get() = voteAverage.formattedRating
}

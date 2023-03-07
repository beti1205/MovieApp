/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.persontvseriescredits.data

import com.beti1205.movieapp.utils.formattedRating
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PersonTVSeriesCast(
    val id: Int,
    val character: String,
    val name: String,
    val popularity: Double,
    val overview: String,

    @SerialName("credit_id")
    val creditId: String,

    @SerialName("original_name")
    val originalName: String,

    @SerialName("genre_ids")
    val genreIds: List<Int>,

    @SerialName("poster_path")
    val poster: String?,

    @SerialName("vote_count")
    val voteCount: Int,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("episode_count")
    val episodeCount: Int?,

    @SerialName("original_language")
    val originalLanguage: String,

    @SerialName("first_air_date")
    val firstAirDate: String,

    @SerialName("backdrop_path")
    val backdropPath: String?,

    @SerialName("origin_country")
    val originCountry: List<String>
) {
    val votes: String
        get() = voteAverage.formattedRating
}

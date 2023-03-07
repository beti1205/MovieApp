/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.moviedetails.data

import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.utils.formattedRating
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MovieDetails(
    val id: Int,
    val genres: List<Genre>,
    val title: String,
    val overview: String,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("release_date")
    val releaseDate: String?
) {
    val votes: String
        get() = voteAverage.formattedRating
}

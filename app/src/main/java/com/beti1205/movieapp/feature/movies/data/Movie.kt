/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.movies.data

import android.os.Parcelable
import com.beti1205.movieapp.utils.formattedRating
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class Movie(
    val id: Int,
    val title: String,
    val overview: String,
    val popularity: Double,
    val adult: Boolean,

    @SerialName("vote_count")
    val voteCount: Int,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("original_language")
    val language: String,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("original_title")
    val originalTitle: String,

    @SerialName("release_date")
    val releaseDate: String?
) : Parcelable {

    val votes: String
        get() = voteAverage.formattedRating
}

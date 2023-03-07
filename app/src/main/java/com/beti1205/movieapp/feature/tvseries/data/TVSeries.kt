/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvseries.data

import android.os.Parcelable
import com.beti1205.movieapp.utils.formattedRating
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Parcelize
@Serializable
data class TVSeries(
    val popularity: Double,
    val id: Int,
    val overview: String,
    val name: String,

    @SerialName("first_air_date")
    val firstAirDate: String?,

    @SerialName("original_name")
    val originalName: String,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("poster_path")
    val posterPath: String?
) : Parcelable {

    val votes: String
        get() = voteAverage.formattedRating
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvseries.data

import android.os.Parcelable
import com.beti1205.movieapp.utils.formattedRating
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TVSeries(
    val popularity: Double,
    val id: Int,
    val overview: String,
    val name: String,

    @Json(name = "first_air_date")
    val firstAirDate: String?,

    @Json(name = "original_name")
    val originalName: String,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "poster_path")
    val posterPath: String?
) : Parcelable {

    val votes: String
        get() = voteAverage.formattedRating
}

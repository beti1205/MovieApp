/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvepisodes.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Episode(
    val id: Int,
    val name: String,
    val overview: String,

    @Json(name = "still_path")
    val posterPath: String?,

    @Json(name = "air_date")
    val episodeAirDate: String,

    @Json(name = "episode_number")
    val episodeNumber: Int
) {
    val numberOfEpisode = episodeNumber.toString()
}

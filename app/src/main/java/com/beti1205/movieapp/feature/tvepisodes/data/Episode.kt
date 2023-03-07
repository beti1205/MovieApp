/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvepisodes.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Episode(
    val id: Int,
    val name: String,
    val overview: String,

    @SerialName("still_path")
    val posterPath: String?,

    @SerialName("air_date")
    val episodeAirDate: String,

    @SerialName("episode_number")
    val episodeNumber: Int
) {
    val numberOfEpisode = episodeNumber.toString()
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvseriesdetails.data

import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.utils.formattedRating
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TVSeriesDetails(
    val id: Int,
    val genres: List<Genre>,
    val seasons: List<Season>,
    val overview: String,
    val name: String,

    @SerialName("vote_average")
    val voteAverage: Double,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("first_air_date")
    val firstAirDate: String,

    @SerialName("in_production")
    val inProduction: Boolean,

    @SerialName("last_air_date")
    val lastAirDate: String,

    @SerialName("number_of_episodes")
    val numberOfEpisodes: Int,

    @SerialName("number_of_seasons")
    val numberOfSeasons: Int
) {
    val votes: String
        get() = voteAverage.formattedRating
}

@Serializable
data class Season(
    val id: Int,
    val name: String,
    val overview: String,

    @SerialName("episode_count")
    val episodeCount: Int,

    @SerialName("air_date")
    val airDate: String?,

    @SerialName("poster_path")
    val posterPath: String?,

    @SerialName("season_number")
    val seasonNumber: Int
) {
    val episodes = episodeCount.toString()
}

package com.beti1205.movieapp.feature.fetchtvseriesdetails.data

import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.utils.formattedRating
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TVSeriesDetails(
    val id: Int,
    val genres: List<Genre>,
    val seasons: List<Season>,
    val overview: String,
    val name: String,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "first_air_date")
    val firstAirDate: String,

    @Json(name = "in_production")
    val inProduction: Boolean,

    @Json(name = "last_air_date")
    val lastAirDate: String,

    @Json(name = "number_of_episodes")
    val numberOfEpisodes: Int,

    @Json(name = "number_of_seasons")
    val numberOfSeasons: Int
) {
    val votes: String
        get() = voteAverage.formattedRating
}

@JsonClass(generateAdapter = true)
data class Season(
    val id: Int,
    val name: String,
    val overview: String,

    @Json(name = "episode_count")
    val episodeCount: Int,

    @Json(name = "air_date")
    val airDate: String?,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "season_number")
    val seasonNumber: Int
) {
    val episodes = episodeCount.toString()
}

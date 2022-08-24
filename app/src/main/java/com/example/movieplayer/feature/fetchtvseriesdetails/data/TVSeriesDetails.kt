package com.example.movieplayer.feature.fetchtvseriesdetails.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TVSeriesDetails(
    @Json(name = "first_air_date")
    val firstAirDate: String,
    val genres: List<Genre>,
    @Json(name = "in_production")
    val inProduction: Boolean,
    @Json(name = "last_air_date")
    val lastAirDate: String,
    @Json(name = "number_of_episodes")
    val numberOfEpisodes: Int,
    @Json(name = "number_of_seasons")
    val numberOfSeasons: Int,
    val seasons: List<Season>
)

@JsonClass(generateAdapter = true)
data class Genre(
    val id: Int,
    val name: String
)

@JsonClass(generateAdapter = true)
data class Season(
    @Json(name = "air_date")
    val airDate: String?,
    @Json(name = "episode_count")
    val episodeCount: Int,
    val id: Int,
    val name: String,
    val overview: String,
    @Json(name = "poster_path")
    val posterPath: String?,
    @Json(name = "season_number")
    val seasonNumber: Int
) {
    fun episodes() = episodeCount.toString()
}

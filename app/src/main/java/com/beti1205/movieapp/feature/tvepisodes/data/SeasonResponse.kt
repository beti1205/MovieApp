package com.beti1205.movieapp.feature.tvepisodes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeasonResponse(
    val episodes: List<Episode>
)

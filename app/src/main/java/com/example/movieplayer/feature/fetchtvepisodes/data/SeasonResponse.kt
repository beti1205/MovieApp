package com.example.movieplayer.feature.fetchtvepisodes.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SeasonResponse(
    val episodes: List<Episode>
)

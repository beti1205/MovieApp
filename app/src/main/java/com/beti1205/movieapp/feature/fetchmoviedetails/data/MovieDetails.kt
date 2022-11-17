package com.beti1205.movieapp.feature.fetchmoviedetails.data

import com.beti1205.movieapp.common.Genre
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MovieDetails(
    val genres: List<Genre>
)

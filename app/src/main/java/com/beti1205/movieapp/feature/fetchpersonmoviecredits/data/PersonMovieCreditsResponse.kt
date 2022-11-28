package com.beti1205.movieapp.feature.fetchpersonmoviecredits.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PersonMovieCreditsResponse(
    val cast: List<PersonMovieCast>,
    val crew: List<PersonMovieCrew>,
    val id: Int
)

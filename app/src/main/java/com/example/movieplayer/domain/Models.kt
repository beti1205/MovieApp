package com.example.movieplayer.domain

data class MovieModel(
    val id: Int,
    val posterPath: String?,
    val originalTitle: String,
    val releaseDate: String
)
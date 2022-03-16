package com.example.movieplayer.network

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Movie(
    val id: Int,

    val title: String,

    val overview: String,

    val popularity: Double,

    val adult: Boolean,

    @Json(name = "vote_count")
    val voteCount : Int,

    @Json(name = "vote_average")
    val voteAverage : Double,

    @Json(name = "original_language")
    val language : String,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "original_title")
    val originalTitle: String,

    @Json(name = "release_date")
    val releaseDate: String
)

fun List<Movie>.asDomainModel(): List<com.example.movieplayer.domain.Movie> {
    return map {
        com.example.movieplayer.domain.Movie(
            id = it.id,
            title = it.title,
            overview = it.overview,
            popularity = it.popularity,
            adult = it.adult,
            voteCount = it.voteCount,
            voteAverage = it.voteAverage,
            language = it.language,
            posterPath = it.posterPath,
            originalTitle = it.originalTitle,
            releaseDate = it.releaseDate
        )
    }
}

fun List<Movie>.asDatabaseModel(): List<com.example.movieplayer.database.Movie> {
    return map {
        com.example.movieplayer.database.Movie(
            id = it.id,
            title = it.title,
            overview = it.overview,
            popularity = it.popularity,
            adult = it.adult,
            voteCount = it.voteCount,
            voteAverage = it.voteAverage,
            language = it.language,
            posterPath = it.posterPath,
            originalTitle = it.originalTitle,
            releaseDate = it.releaseDate
        )
    }
}

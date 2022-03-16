package com.example.movieplayer.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieplayer.domain.Movie
import com.squareup.moshi.Json

@Entity
data class Movie constructor(
    @PrimaryKey
    val id: Int,
    val title: String,
    val overview: String,
    val popularity: Double,
    val adult: Boolean,
    val voteCount : Int,
    val voteAverage : Double,
    val language : String,
    val posterPath: String?,
    val originalTitle: String,
    val releaseDate: String
)

fun List<com.example.movieplayer.database.Movie>.asDomainModel(): List<Movie> {
    return map {
        Movie(
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
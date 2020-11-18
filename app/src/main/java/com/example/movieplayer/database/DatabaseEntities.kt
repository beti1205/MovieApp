package com.example.movieplayer.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.movieplayer.domain.MovieModel

@Entity
data class DatabaseMovie constructor(
    @PrimaryKey
    val id: Int,
    val posterPath: String?,
    val originalTitle: String,
    val releaseDate: String
)

fun List<DatabaseMovie>.asDomainModel(): List<MovieModel> {
    return map {
        MovieModel(
            id = it.id,
            posterPath = it.posterPath,
            originalTitle = it.originalTitle,
            releaseDate = it.releaseDate
        )
    }
}
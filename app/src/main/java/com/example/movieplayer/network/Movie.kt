package com.example.movieplayer.network

import android.os.Parcelable
import android.provider.ContactsContract
import com.example.movieplayer.database.DatabaseMovie
import com.example.movieplayer.domain.MovieModel
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Movie(
    val id: Int,

    @Json(name = "poster_path")
    val posterPath: String?,

    @Json(name = "original_title")
    val originalTitle: String,

    @Json(name = "release_date")
    val releaseDate: String
) : Parcelable

fun List<Movie>.asDomainModel(): List<MovieModel> {
    return map {
        MovieModel(
            id = it.id,
            posterPath = it.posterPath,
            originalTitle = it.originalTitle,
            releaseDate = it.releaseDate
        )
    }
}

fun List<Movie>.asDatabaseModel(): List<DatabaseMovie> {
    return map {
        DatabaseMovie(
            id = it.id,
            posterPath = it.posterPath,
            originalTitle = it.originalTitle,
            releaseDate = it.releaseDate
        )
    }
}

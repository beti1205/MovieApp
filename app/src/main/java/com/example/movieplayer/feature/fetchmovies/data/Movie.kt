package com.example.movieplayer.feature.fetchmovies.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class Movie(
        val id: Int,

        val title: String,

        val overview: String,

        val popularity: Double,

        val adult: Boolean,

        @Json(name = "vote_count")
        val voteCount: Int,

        @Json(name = "vote_average")
        val voteAverage: Double,

        @Json(name = "original_language")
        val language: String,

        @Json(name = "poster_path")
        val posterPath: String?,

        @Json(name = "original_title")
        val originalTitle: String,

        @Json(name = "release_date")
        val releaseDate: String
): Parcelable {
    fun votes() = voteAverage.toString()
}



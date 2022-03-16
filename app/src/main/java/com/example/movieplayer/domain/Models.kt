package com.example.movieplayer.domain

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
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
): Parcelable {
    fun votes() = voteAverage.toString()
}
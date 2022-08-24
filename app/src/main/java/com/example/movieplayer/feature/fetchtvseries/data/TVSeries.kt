package com.example.movieplayer.feature.fetchtvseries.data

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.android.parcel.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class TVSeries(

    val popularity: Double,

    val id: Int,

    val overview: String,

    val name: String,

    @Json(name = "first_air_date")
    val firstAirDate: String?,

    @Json(name = "original_name")
    val originalName: String,

    @Json(name = "vote_average")
    val voteAverage: Double,

    @Json(name = "poster_path")
    val posterPath: String?
) : Parcelable {
    fun votes() = voteAverage.toString()
}

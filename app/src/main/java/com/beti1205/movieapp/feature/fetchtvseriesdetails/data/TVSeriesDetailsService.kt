package com.beti1205.movieapp.feature.fetchtvseriesdetails.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVSeriesDetailsService {
    @GET("tv/{tv_id}")
    suspend fun getTVSeriesDetails(
        @Path("tv_id") tvId: Int,
        @Query("api_key") key: String
    ): TVSeriesDetails
}

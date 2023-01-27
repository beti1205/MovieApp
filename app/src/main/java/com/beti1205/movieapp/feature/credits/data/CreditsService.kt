package com.beti1205.movieapp.feature.credits.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CreditsService {
    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") id: Int, @Query("api_key") key: String): Credits

    @GET("tv/{tv_id}/credits")
    suspend fun getTVSeriesCredits(@Path("tv_id") id: Int, @Query("api_key") key: String): Credits
}

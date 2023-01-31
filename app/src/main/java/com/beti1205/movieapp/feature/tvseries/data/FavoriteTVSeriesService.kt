package com.beti1205.movieapp.feature.tvseries.data

import com.beti1205.movieapp.common.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FavoriteTVSeriesService {
    @GET("account/{account_id}/favorite/tv")
    suspend fun getFavoriteTVSeries(
        @Path("account_id") accountId: Int,
        @Query("api_key") key: String,
        @Query("session_id") sessionId: String
    ): ApiResponse<TVSeries>
}

package com.beti1205.movieapp.feature.markfavorite.data

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface MarkFavoriteService {
    @POST("account/{account_id}/favorite")
    suspend fun markFavorite(
        @Path("account_id") accountId: Int,
        @Query("api_key") key: String,
        @Query("session_id") sessionId: String,
        @Body body: MarkFavoriteBody
    ): MarkFavoriteResponse
}

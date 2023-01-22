package com.beti1205.movieapp.feature.fetchaccountstates.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AccountStatesService {
    @GET("movie/{movie_id}/account_states")
    suspend fun getAccountStates(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("session_id") sessionId: String
    ): AccountStates
}

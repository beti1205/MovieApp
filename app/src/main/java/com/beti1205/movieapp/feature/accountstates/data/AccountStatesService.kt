/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.accountstates.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AccountStatesService {
    @GET("movie/{movie_id}/account_states")
    suspend fun getMoviesAccountStates(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String,
        @Query("session_id") sessionId: String
    ): AccountStates

    @GET("tv/{tv_id}/account_states")
    suspend fun getTVAccountStates(
        @Path("tv_id") tvId: Int,
        @Query("api_key") key: String,
        @Query("session_id") sessionId: String
    ): AccountStates
}

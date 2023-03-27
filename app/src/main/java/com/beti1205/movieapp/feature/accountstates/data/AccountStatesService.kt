/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.accountstates.data

import com.beti1205.movieapp.common.auth.SessionRequired
import retrofit2.http.GET
import retrofit2.http.Path

interface AccountStatesService {
    @GET("movie/{movie_id}/account_states")
    @SessionRequired
    suspend fun getMoviesAccountStates(
        @Path("movie_id") movieId: Int
    ): AccountStates

    @GET("tv/{tv_id}/account_states")
    @SessionRequired
    suspend fun getTVAccountStates(
        @Path("tv_id") tvId: Int
    ): AccountStates
}

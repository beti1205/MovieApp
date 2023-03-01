/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.watchlist.data

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query

interface AddToWatchlistService {
    @POST("account/{account_id}/watchlist")
    suspend fun addToWatchlist(
        @Path("account_id") accountId: Int,
        @Query("api_key") key: String,
        @Query("session_id") sessionId: String,
        @Body body: AddToWatchlistBody
    ): AddToWatchlistResponse
}

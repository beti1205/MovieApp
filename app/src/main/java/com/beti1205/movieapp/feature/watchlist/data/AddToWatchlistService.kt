/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.watchlist.data

import com.beti1205.movieapp.common.auth.SessionRequired
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface AddToWatchlistService {
    @POST("account/{account_id}/watchlist")
    @SessionRequired
    suspend fun addToWatchlist(
        @Path("account_id") accountId: Int,
        @Body body: AddToWatchlistBody
    ): AddToWatchlistResponse
}

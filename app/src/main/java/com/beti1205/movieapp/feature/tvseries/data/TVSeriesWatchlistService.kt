/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvseries.data

import com.beti1205.movieapp.common.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVSeriesWatchlistService {
    @GET("account/{account_id}/watchlist/tv")
    suspend fun getTVSeriesWatchlist(
        @Path("account_id") accountId: Int,
        @Query("sort_by") sortBy: String,
        @Query("api_key") key: String,
        @Query("session_id") sessionId: String
    ): ApiResponse<TVSeries>
}

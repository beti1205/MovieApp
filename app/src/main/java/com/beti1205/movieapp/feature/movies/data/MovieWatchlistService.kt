/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.movies.data

import com.beti1205.movieapp.common.auth.SessionRequired
import com.beti1205.movieapp.common.data.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieWatchlistService {
    @GET("account/{account_id}/watchlist/movies")
    @SessionRequired
    suspend fun getMovieWatchlist(
        @Path("account_id") accountId: Int,
        @Query("sort_by") sortBy: String
    ): ApiResponse<Movie>
}

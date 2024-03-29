/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.credits.data

import retrofit2.http.GET
import retrofit2.http.Path

interface CreditsService {
    @GET("movie/{movie_id}/credits")
    suspend fun getMovieCredits(@Path("movie_id") id: Int): Credits

    @GET("tv/{tv_id}/credits")
    suspend fun getTVSeriesCredits(@Path("tv_id") id: Int): Credits
}

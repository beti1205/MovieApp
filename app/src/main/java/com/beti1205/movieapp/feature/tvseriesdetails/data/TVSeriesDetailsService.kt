/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvseriesdetails.data

import retrofit2.http.GET
import retrofit2.http.Path

interface TVSeriesDetailsService {
    @GET("tv/{tv_id}")
    suspend fun getTVSeriesDetails(
        @Path("tv_id") tvId: Int
    ): TVSeriesDetails
}

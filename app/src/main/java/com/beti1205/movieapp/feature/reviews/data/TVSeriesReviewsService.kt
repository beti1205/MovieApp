/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.reviews.data

import retrofit2.http.GET
import retrofit2.http.Path

interface TVSeriesReviewsService {
    @GET("tv/{tv_id}/reviews")
    suspend fun getTVSeriesReviews(
        @Path("tv_id") tvId: Int
    ): ReviewsResult
}

package com.beti1205.movieapp.feature.reviews.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface TVSeriesReviewsService {
    @GET("tv/{tv_id}/reviews")
    suspend fun getTVSeriesReviews(
        @Path("tv_id") tvId: Int,
        @Query("api_key") key: String
    ): ReviewsResult
}

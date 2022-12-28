package com.beti1205.movieapp.feature.fetchmoviereviews.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface MovieReviewsService {
    @GET("movie/{movie_id}/reviews")
    suspend fun getMovieReviews(
        @Path("movie_id") movieId: Int,
        @Query("api_key") key: String
    ): MovieReviewsResult
}

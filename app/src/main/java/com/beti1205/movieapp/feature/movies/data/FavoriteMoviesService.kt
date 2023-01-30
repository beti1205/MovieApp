package com.beti1205.movieapp.feature.movies.data

import com.beti1205.movieapp.common.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface FavoriteMoviesService {
    @GET("account/{account_id}/favorite/movies")
    suspend fun getFavoriteMovies(
        @Path("account_id") accountId: Int,
        @Query("api_key") key: String,
        @Query("session_id") sessionId: String
    ): ApiResponse<Movie>
}
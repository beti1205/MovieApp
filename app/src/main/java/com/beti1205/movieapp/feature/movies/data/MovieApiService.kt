/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.movies.data

import com.beti1205.movieapp.common.data.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("page") page: Int
    ): ApiResponse<Movie>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("page") page: Int
    ): ApiResponse<Movie>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("page") page: Int
    ): ApiResponse<Movie>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("page") page: Int
    ): ApiResponse<Movie>

    @GET("search/movie")
    suspend fun getSearchedMovies(
        @Query("query") query: String,
        @Query("page") page: Int
    ): ApiResponse<Movie>
}

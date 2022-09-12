package com.example.movieplayer.feature.fetchmovies.data

import com.example.movieplayer.common.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieApiService {
    @GET("movie/popular")
    suspend fun getPopularMovies(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): ApiResponse<Movie>

    @GET("movie/top_rated")
    suspend fun getTopRatedMovies(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): ApiResponse<Movie>

    @GET("movie/upcoming")
    suspend fun getUpcomingMovies(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): ApiResponse<Movie>

    @GET("movie/now_playing")
    suspend fun getNowPlayingMovies(
        @Query("api_key") key: String,
        @Query("page") page: Int
    ): ApiResponse<Movie>

    @GET("search/movie")
    suspend fun getSearchedMovies(
        @Query("api_key") key: String,
        @Query("query") query: String,
        @Query("page") page: Int
    ): ApiResponse<Movie>
}

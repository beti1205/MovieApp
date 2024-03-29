/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvseries.data

import com.beti1205.movieapp.common.data.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface TVSeriesService {
    @GET("tv/popular")
    suspend fun getPopularTVSeries(
        @Query("page") page: Int
    ): ApiResponse<TVSeries>

    @GET("tv/top_rated")
    suspend fun getTopRatedTVSeries(
        @Query("page") page: Int
    ): ApiResponse<TVSeries>

    @GET("tv/airing_today")
    suspend fun getAiringToday(
        @Query("page") page: Int
    ): ApiResponse<TVSeries>

    @GET("tv/on_the_air")
    suspend fun getOnTheAir(
        @Query("page") page: Int
    ): ApiResponse<TVSeries>

    @GET("search/tv")
    suspend fun getSearchedTVSeries(
        @Query("query") query: String,
        @Query("page") page: Int
    ): ApiResponse<TVSeries>
}

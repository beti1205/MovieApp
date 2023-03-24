/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.movies.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.data.ApiResponse
import com.beti1205.movieapp.common.flatMap
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.feature.movies.data.MovieApiService
import javax.inject.Inject

interface FetchMoviesUseCase {

    suspend operator fun invoke(
        movieOrder: MovieOrder,
        page: Int
    ): Result<ApiResponse<Movie>>
}

class FetchMoviesUseCaseImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val appConfig: AppConfig
) : FetchMoviesUseCase {

    override suspend fun invoke(movieOrder: MovieOrder, page: Int): Result<ApiResponse<Movie>> {
        return performRequest {
            when (movieOrder) {
                MovieOrder.POPULAR -> movieApiService.getPopularMovies(appConfig.apiKey, page)
                MovieOrder.UPCOMING -> movieApiService.getUpcomingMovies(appConfig.apiKey, page)
                MovieOrder.TOP_RATED -> movieApiService.getTopRatedMovies(appConfig.apiKey, page)
                MovieOrder.NOW_PLAYING -> movieApiService.getNowPlayingMovies(
                    appConfig.apiKey,
                    page
                )
            }
        }.flatMap { result ->
            Result.Success(
                data = result.transformMoviePosterPath(appConfig.imageUrl)
            )
        }
    }
}

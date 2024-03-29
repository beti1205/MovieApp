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

interface SearchMoviesUseCase {

    suspend operator fun invoke(
        query: String,
        page: Int
    ): Result<ApiResponse<Movie>>
}

class SearchMoviesUseCaseImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val appConfig: AppConfig
) : SearchMoviesUseCase {

    override suspend fun invoke(query: String, page: Int): Result<ApiResponse<Movie>> {
        return performRequest {
            movieApiService.getSearchedMovies(
                query = query,
                page = page
            )
        }.flatMap { result ->
            Result.Success(
                data = result.transformMoviePosterPath(appConfig.imageUrl)
            )
        }
    }
}

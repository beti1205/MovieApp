/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.movies.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.auth.AuthManager
import com.beti1205.movieapp.common.data.ApiResponse
import com.beti1205.movieapp.common.data.ListOrder
import com.beti1205.movieapp.common.exceptions.NotLoggedInException
import com.beti1205.movieapp.common.flatMap
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.feature.movies.data.MovieWatchlistService
import javax.inject.Inject

interface FetchMovieWatchlistUseCase {

    suspend operator fun invoke(
        order: ListOrder
    ): Result<ApiResponse<Movie>>
}

class FetchMovieWatchlistUseCaseImpl @Inject constructor(
    private val movieWatchlistService: MovieWatchlistService,
    private val appConfig: AppConfig,
    private val authManager: AuthManager
) : FetchMovieWatchlistUseCase {

    override suspend fun invoke(order: ListOrder): Result<ApiResponse<Movie>> {
        if (!authManager.isLoggedIn) {
            return Result.Error(NotLoggedInException)
        }
        return performRequest {
            movieWatchlistService.getMovieWatchlist(
                accountId = authManager.accountId,
                sortBy = order.type
            )
        }.flatMap { result ->
            Result.Success(
                data = result.transformMoviePosterPath(appConfig.imageUrl)
            )
        }
    }
}

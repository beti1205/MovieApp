/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.movies.domain

import com.beti1205.movieapp.common.ApiResponse
import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.GenericApiException
import com.beti1205.movieapp.common.ListOrder
import com.beti1205.movieapp.common.Result
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
            return Result.Error(GenericApiException)
        }
        return performRequest {
            movieWatchlistService.getMovieWatchlist(
                accountId = authManager.accountId,
                sortBy = order.type,
                key = appConfig.apiKey,
                sessionId = authManager.sessionId!!
            )
        }.flatMap { result ->
            Result.Success(
                result.transformMoviePosterPath(appConfig.imageUrl)
            )
        }
    }
}

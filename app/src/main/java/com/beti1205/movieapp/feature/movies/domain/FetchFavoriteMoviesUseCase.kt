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
import com.beti1205.movieapp.feature.movies.data.FavoriteMoviesService
import com.beti1205.movieapp.feature.movies.data.Movie
import javax.inject.Inject

interface FetchFavoriteMoviesUseCase {

    suspend operator fun invoke(
        order: ListOrder
    ): Result<ApiResponse<Movie>>
}

class FetchFavoriteMoviesUseCaseImpl @Inject constructor(
    private val favoriteMoviesService: FavoriteMoviesService,
    private val appConfig: AppConfig,
    private val authManager: AuthManager
) : FetchFavoriteMoviesUseCase {

    override suspend fun invoke(order: ListOrder): Result<ApiResponse<Movie>> {
        if (!authManager.isLoggedIn) {
            return Result.Error(NotLoggedInException)
        }
        return performRequest {
            favoriteMoviesService.getFavoriteMovies(
                accountId = authManager.accountId,
                sortBy = order.type,
                sessionId = authManager.sessionId!!
            )
        }.flatMap { result ->
            Result.Success(
                data = result.transformMoviePosterPath(appConfig.imageUrl)
            )
        }
    }
}

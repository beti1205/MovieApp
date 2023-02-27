/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.movies.domain

import com.beti1205.movieapp.common.ApiResponse
import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.FavoriteListOrder
import com.beti1205.movieapp.common.GenericApiException
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.flatMap
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.movies.data.FavoriteMoviesService
import com.beti1205.movieapp.feature.movies.data.Movie
import javax.inject.Inject

interface FetchFavoriteMoviesUseCase {

    suspend operator fun invoke(
        order: FavoriteListOrder
    ): Result<ApiResponse<Movie>>
}

class FetchFavoriteMoviesUseCaseImpl @Inject constructor(
    private val favoriteMoviesService: FavoriteMoviesService,
    private val appConfig: AppConfig,
    private val authManager: AuthManager
) : FetchFavoriteMoviesUseCase {

    override suspend fun invoke(order: FavoriteListOrder): Result<ApiResponse<Movie>> {
        if (!authManager.isLoggedIn) {
            return Result.Error(GenericApiException)
        }
        return performRequest {
            favoriteMoviesService.getFavoriteMovies(
                accountId = authManager.accountId,
                sortBy = order.type,
                key = appConfig.apiKey,
                sessionId = authManager.sessionId!!
            )
        }.flatMap { result ->
            Result.Success(
                result.copy(
                    items = result.items.map { movie ->
                        val poster = when {
                            movie.posterPath != null -> "${appConfig.imageUrl}${movie.posterPath}"
                            else -> null
                        }
                        movie.copy(
                            posterPath = poster
                        )
                    }
                )
            )
        }
    }
}

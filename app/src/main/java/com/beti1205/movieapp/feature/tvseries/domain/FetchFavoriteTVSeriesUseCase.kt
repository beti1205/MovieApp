/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvseries.domain

import com.beti1205.movieapp.common.ApiResponse
import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.FavoriteListOrder
import com.beti1205.movieapp.common.GenericApiException
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.flatMap
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.tvseries.data.FavoriteTVSeriesService
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import javax.inject.Inject

interface FetchFavoriteTVSeriesUseCase {

    suspend operator fun invoke(
        order: FavoriteListOrder
    ): Result<ApiResponse<TVSeries>>
}

class FetchFavoriteTVSeriesUseCaseImpl @Inject constructor(
    private val favoriteTVSeriesService: FavoriteTVSeriesService,
    private val appConfig: AppConfig,
    private val authManager: AuthManager
) : FetchFavoriteTVSeriesUseCase {

    override suspend fun invoke(order: FavoriteListOrder): Result<ApiResponse<TVSeries>> {
        if (!authManager.isLoggedIn) {
            return Result.Error(GenericApiException)
        }
        return performRequest {
            favoriteTVSeriesService.getFavoriteTVSeries(
                accountId = authManager.accountId,
                sortBy = order.type,
                key = appConfig.apiKey,
                sessionId = authManager.sessionId!!
            )
        }.flatMap { result ->
            Result.Success(
                result.copy(
                    items = result.items.map { tvSeries ->
                        val poster = when {
                            tvSeries.posterPath != null -> "${appConfig.imageUrl}${tvSeries.posterPath}"
                            else -> null
                        }
                        tvSeries.copy(
                            posterPath = poster
                        )
                    }
                )
            )
        }
    }
}

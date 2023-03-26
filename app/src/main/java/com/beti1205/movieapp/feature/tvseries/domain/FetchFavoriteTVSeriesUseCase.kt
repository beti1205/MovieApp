/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvseries.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.auth.AuthManager
import com.beti1205.movieapp.common.data.ApiResponse
import com.beti1205.movieapp.common.data.ListOrder
import com.beti1205.movieapp.common.exceptions.NotLoggedInException
import com.beti1205.movieapp.common.flatMap
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.tvseries.data.FavoriteTVSeriesService
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import javax.inject.Inject

interface FetchFavoriteTVSeriesUseCase {

    suspend operator fun invoke(
        order: ListOrder
    ): Result<ApiResponse<TVSeries>>
}

class FetchFavoriteTVSeriesUseCaseImpl @Inject constructor(
    private val favoriteTVSeriesService: FavoriteTVSeriesService,
    private val appConfig: AppConfig,
    private val authManager: AuthManager
) : FetchFavoriteTVSeriesUseCase {

    override suspend fun invoke(order: ListOrder): Result<ApiResponse<TVSeries>> {
        if (!authManager.isLoggedIn) {
            return Result.Error(NotLoggedInException)
        }
        return performRequest {
            favoriteTVSeriesService.getFavoriteTVSeries(
                accountId = authManager.accountId,
                sortBy = order.type,
                sessionId = authManager.sessionId!!
            )
        }.flatMap { result ->
            Result.Success(
                result.transformTVSeriesPosterPath(appConfig.imageUrl)
            )
        }
    }
}

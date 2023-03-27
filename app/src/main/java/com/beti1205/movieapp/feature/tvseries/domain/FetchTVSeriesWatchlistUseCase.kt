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
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.feature.tvseries.data.TVSeriesWatchlistService
import javax.inject.Inject

interface FetchTVSeriesWatchlistUseCase {

    suspend operator fun invoke(
        order: ListOrder
    ): Result<ApiResponse<TVSeries>>
}

class FetchTVSeriesWatchlistUseCaseImpl @Inject constructor(
    private val tvSeriesWatchlistService: TVSeriesWatchlistService,
    private val appConfig: AppConfig,
    private val authManager: AuthManager
) : FetchTVSeriesWatchlistUseCase {

    override suspend fun invoke(order: ListOrder): Result<ApiResponse<TVSeries>> {
        if (!authManager.isLoggedIn) {
            return Result.Error(NotLoggedInException)
        }
        return performRequest {
            tvSeriesWatchlistService.getTVSeriesWatchlist(
                accountId = authManager.accountId,
                sortBy = order.type
            )
        }.flatMap { result ->
            Result.Success(
                data = result.transformTVSeriesPosterPath(appConfig.imageUrl)
            )
        }
    }
}

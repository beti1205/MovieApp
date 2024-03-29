/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvseries.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.data.ApiResponse
import com.beti1205.movieapp.common.flatMap
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.feature.tvseries.data.TVSeriesService
import javax.inject.Inject

interface SearchTVSeriesUseCase {

    suspend operator fun invoke(
        query: String,
        page: Int
    ): Result<ApiResponse<TVSeries>>
}

class SearchTVSeriesUseCaseImpl @Inject constructor(
    private val tvSeriesService: TVSeriesService,
    private val appConfig: AppConfig
) : SearchTVSeriesUseCase {

    override suspend fun invoke(query: String, page: Int): Result<ApiResponse<TVSeries>> {
        return performRequest {
            tvSeriesService.getSearchedTVSeries(
                query = query,
                page = page
            )
        }.flatMap { result ->
            Result.Success(
                data = result.transformTVSeriesPosterPath(appConfig.imageUrl)
            )
        }
    }
}

package com.beti1205.movieapp.feature.fetchtvseries.domain

import com.beti1205.movieapp.common.ApiResponse
import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeriesService
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
        return performRequest { tvSeriesService.getSearchedTVSeries(appConfig.apiKey, query, page) }
    }
}

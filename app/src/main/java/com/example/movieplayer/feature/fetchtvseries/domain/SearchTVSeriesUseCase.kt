package com.example.movieplayer.feature.fetchtvseries.domain

import com.example.movieplayer.common.ApiResponse
import com.example.movieplayer.common.AppConfig
import com.example.movieplayer.common.Result
import com.example.movieplayer.common.performRequest
import com.example.movieplayer.feature.fetchtvseries.data.TVSeries
import com.example.movieplayer.feature.fetchtvseries.data.TVSeriesService
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

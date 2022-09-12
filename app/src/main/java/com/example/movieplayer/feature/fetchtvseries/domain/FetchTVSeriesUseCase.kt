package com.example.movieplayer.feature.fetchtvseries.domain

import com.example.movieplayer.common.ApiResponse
import com.example.movieplayer.common.AppConfig
import com.example.movieplayer.common.Result
import com.example.movieplayer.common.performRequest
import com.example.movieplayer.feature.fetchtvseries.data.TVSeries
import com.example.movieplayer.feature.fetchtvseries.data.TVSeriesService
import javax.inject.Inject

interface FetchTVSeriesUseCase {

    suspend operator fun invoke(
        tvOrder: TVOrder,
        page: Int
    ): Result<ApiResponse<TVSeries>>
}

class FetchTVSeriesUseCaseImpl @Inject constructor(
    private val tvSeriesService: TVSeriesService,
    private val appConfig: AppConfig
) : FetchTVSeriesUseCase {

    override suspend fun invoke(tvOrder: TVOrder, page: Int): Result<ApiResponse<TVSeries>> {
        return performRequest {
            when (tvOrder) {
                TVOrder.POPULAR -> tvSeriesService.getPopularTVSeries(appConfig.apiKey, page)
                TVOrder.TOP_RATED -> tvSeriesService.getTopRatedTVSeries(appConfig.apiKey, page)
                TVOrder.AIRING_TODAY -> tvSeriesService.getAiringToday(appConfig.apiKey, page)
                TVOrder.ON_THE_AIR -> tvSeriesService.getOnTheAir(appConfig.apiKey, page)
            }
        }
    }
}

package com.beti1205.movieapp.feature.tvseries.domain

import com.beti1205.movieapp.common.ApiResponse
import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.feature.tvseries.data.TVSeriesService
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

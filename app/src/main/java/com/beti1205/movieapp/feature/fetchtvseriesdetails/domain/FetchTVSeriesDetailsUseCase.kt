package com.beti1205.movieapp.feature.fetchtvseriesdetails.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.TVSeriesDetails
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.TVSeriesDetailsService
import javax.inject.Inject

interface FetchTVSeriesDetailsUseCase {

    suspend operator fun invoke(
        tvId: Int
    ): Result<TVSeriesDetails>
}

class FetchTVSeriesDetailsUseCaseImpl @Inject constructor(
    private val tvSeriesDetailsService: TVSeriesDetailsService,
    private val appConfig: AppConfig
) : FetchTVSeriesDetailsUseCase {

    override suspend fun invoke(tvId: Int): Result<TVSeriesDetails> {
        return performRequest { tvSeriesDetailsService.getTVSeriesDetails(tvId, appConfig.apiKey) }
    }
}

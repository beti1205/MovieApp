package com.example.movieplayer.feature.fetchtvseriesdetails.domain

import com.example.movieplayer.common.AppConfig
import com.example.movieplayer.common.Result
import com.example.movieplayer.common.performRequest
import com.example.movieplayer.feature.fetchtvseriesdetails.data.TVSeriesDetails
import com.example.movieplayer.feature.fetchtvseriesdetails.data.TVSeriesDetailsService
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

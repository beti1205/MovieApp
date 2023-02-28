/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvseriesdetails.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.flatMap
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.common.transformImageUrl
import com.beti1205.movieapp.feature.tvseriesdetails.data.TVSeriesDetails
import com.beti1205.movieapp.feature.tvseriesdetails.data.TVSeriesDetailsService
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
        return performRequest {
            tvSeriesDetailsService.getTVSeriesDetails(
                tvId = tvId,
                key = appConfig.apiKey
            )
        }.flatMap { result ->
            Result.Success(
                result.copy(
                    posterPath = transformImageUrl(result.posterPath, appConfig.imageUrl),
                    seasons = result.seasons.map { season ->
                        season.copy(
                            posterPath = transformImageUrl(season.posterPath, appConfig.imageUrl)
                        )
                    }
                )
            )
        }
    }
}

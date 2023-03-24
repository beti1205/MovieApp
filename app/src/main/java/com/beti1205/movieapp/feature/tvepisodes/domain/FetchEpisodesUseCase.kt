/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvepisodes.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.flatMap
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.tvepisodes.data.EpisodeService
import com.beti1205.movieapp.feature.tvepisodes.data.SeasonResponse
import com.beti1205.movieapp.utils.transformImageUrl
import javax.inject.Inject

interface FetchEpisodesUseCase {

    suspend operator fun invoke(
        tvId: Int,
        seasonNumber: Int
    ): Result<SeasonResponse>
}

class FetchEpisodesUseCaseImpl @Inject constructor(
    private val episodeService: EpisodeService,
    private val appConfig: AppConfig
) : FetchEpisodesUseCase {

    override suspend fun invoke(tvId: Int, seasonNumber: Int): Result<SeasonResponse> {
        return performRequest {
            episodeService.getEpisodes(
                tvId,
                seasonNumber,
                appConfig.apiKey
            )
        }.flatMap { result ->
            Result.Success(
                result.copy(
                    episodes = result.episodes.map { episode ->
                        val poster = transformImageUrl(episode.posterPath, appConfig.imageUrl)
                        episode.copy(posterPath = poster)
                    }
                )
            )
        }
    }
}

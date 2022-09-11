package com.example.movieplayer.feature.fetchtvepisodes.domain

import com.example.movieplayer.common.AppConfig
import com.example.movieplayer.common.Result
import com.example.movieplayer.common.performRequest
import com.example.movieplayer.feature.fetchtvepisodes.data.EpisodeService
import com.example.movieplayer.feature.fetchtvepisodes.data.SeasonResponse
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
        }
    }
}

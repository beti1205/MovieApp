package com.beti1205.movieapp.feature.fetchcredits.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.fetchcredits.data.Credits
import com.beti1205.movieapp.feature.fetchcredits.data.CreditsService
import javax.inject.Inject

interface FetchTVSeriesCreditsUseCase {

    suspend operator fun invoke(
        id: Int
    ): Result<Credits>
}

class FetchTVSeriesCreditsUseCaseImpl @Inject constructor(
    private val creditsService: CreditsService,
    private val appConfig: AppConfig
) : FetchTVSeriesCreditsUseCase {

    override suspend fun invoke(id: Int): Result<Credits> {
        val result = performRequest {
            creditsService.getTVSeriesCredits(id, appConfig.apiKey)
        }

        return when (result) {
            is Result.Success -> Result.Success(
                result.data.copy(
                    crew = result.getTransformedCrewList(),
                    cast = result.getTransformedCastList()
                )
            )
            is Result.Error -> result
        }
    }
}

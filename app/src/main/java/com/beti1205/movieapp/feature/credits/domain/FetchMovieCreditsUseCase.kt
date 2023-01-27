package com.beti1205.movieapp.feature.credits.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.credits.data.Credits
import com.beti1205.movieapp.feature.credits.data.CreditsService
import javax.inject.Inject

interface FetchMovieCreditsUseCase {

    suspend operator fun invoke(
        id: Int
    ): Result<Credits>
}

class FetchMovieCreditsUseCaseImpl @Inject constructor(
    private val creditsService: CreditsService,
    private val appConfig: AppConfig
) : FetchMovieCreditsUseCase {

    override suspend fun invoke(id: Int): Result<Credits> {
        val result = performRequest {
            creditsService.getMovieCredits(id, appConfig.apiKey)
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

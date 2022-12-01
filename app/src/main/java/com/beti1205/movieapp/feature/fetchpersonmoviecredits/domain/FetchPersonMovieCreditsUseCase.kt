package com.beti1205.movieapp.feature.fetchpersonmoviecredits.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCreditsResponse
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCreditsService
import javax.inject.Inject

interface FetchPersonMovieCreditsUseCase {

    suspend operator fun invoke(
        personId: Int
    ): Result<PersonMovieCreditsResponse>
}

class FetchPersonMovieCreditsUseCaseImpl @Inject constructor(
    private val personMovieCreditsService: PersonMovieCreditsService,
    private val appConfig: AppConfig
) : FetchPersonMovieCreditsUseCase {
    override suspend fun invoke(personId: Int): Result<PersonMovieCreditsResponse> {
        return performRequest {
            personMovieCreditsService.getPersonMovieCredits(personId, appConfig.apiKey)
        }
    }
}

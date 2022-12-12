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
        val result = performRequest {
            personMovieCreditsService.getPersonMovieCredits(personId, appConfig.apiKey)
        }

        return when (result) {
            is Result.Error -> result
            is Result.Success -> Result.Success(
                result.data.copy(
                    cast = result.data.cast.map { cast ->
                        cast.copy(
                            releaseDate = cast.releaseDate.split("-").first()
                        )
                    }.sortedByDescending { it.releaseDate },
                    crew = result.data.crew.map { crew ->
                        crew.copy(
                            releaseDate = crew.releaseDate.split("-").first()
                        )
                    }.sortedByDescending { it.releaseDate },
                    id = result.data.id
                )
            )
        }
    }
}

package com.beti1205.movieapp.feature.persontvseriescredits.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCreditsResponse
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCreditsService
import javax.inject.Inject

interface FetchPersonTVSeriesCreditsUseCase {

    suspend operator fun invoke(
        personId: Int
    ): Result<PersonTVSeriesCreditsResponse>
}

class FetchPersonTVSeriesCreditsUseCaseImpl @Inject constructor(
    private val personTVSeriesCreditsService: PersonTVSeriesCreditsService,
    private val appConfig: AppConfig
) : FetchPersonTVSeriesCreditsUseCase {
    override suspend fun invoke(personId: Int): Result<PersonTVSeriesCreditsResponse> {
        val result = performRequest {
            personTVSeriesCreditsService.getPersonTVSeriesCredits(personId, appConfig.apiKey)
        }

        return when (result) {
            is Result.Error -> result
            is Result.Success -> Result.Success(
                result.data.copy(
                    cast = result.data.cast.map { cast ->
                        cast.copy(
                            firstAirDate = cast.firstAirDate.split("-").first()
                        )
                    }.sortedByDescending { it.firstAirDate },
                    crew = result.data.crew.map { crew ->
                        crew.copy(
                            firstAirDate = crew.firstAirDate.split("-").first()
                        )
                    }.sortedByDescending { it.firstAirDate },
                    id = result.data.id
                )
            )
        }
    }
}

package com.beti1205.movieapp.feature.fetchpersontvseriescredits.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCreditsResponse
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCreditsService
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
        return performRequest {
            personTVSeriesCreditsService.getPersonTVSeriesCredits(personId, appConfig.apiKey)
        }
    }
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.persontvseriescredits.domain

import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.flatMap
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
    private val personTVSeriesCreditsService: PersonTVSeriesCreditsService
) : FetchPersonTVSeriesCreditsUseCase {
    override suspend fun invoke(personId: Int): Result<PersonTVSeriesCreditsResponse> {
        return performRequest {
            personTVSeriesCreditsService.getPersonTVSeriesCredits(personId)
        }.flatMap { result ->
            Result.Success(
                result.copy(
                    cast = result.cast.map { cast ->
                        cast.copy(
                            firstAirDate = cast.firstAirDate.split("-").first()
                        )
                    }.sortedByDescending { it.firstAirDate },
                    crew = result.crew.map { crew ->
                        crew.copy(
                            firstAirDate = crew.firstAirDate.split("-").first()
                        )
                    }.sortedByDescending { it.firstAirDate },
                    id = result.id
                )
            )
        }
    }
}

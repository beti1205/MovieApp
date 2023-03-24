/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.personmoviecredits.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.flatMap
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCreditsResponse
import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCreditsService
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
        }.flatMap { result ->
            Result.Success(
                result.copy(
                    cast = result.cast.map { cast ->
                        cast.copy(
                            releaseDate = cast.releaseDate.split("-").first()
                        )
                    }.sortedByDescending { it.releaseDate },
                    crew = result.crew.map { crew ->
                        crew.copy(
                            releaseDate = crew.releaseDate.split("-").first()
                        )
                    }.sortedByDescending { it.releaseDate },
                    id = result.id
                )
            )
        }
    }
}

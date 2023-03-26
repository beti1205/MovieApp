/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.persondetails.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.flatMap
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.persondetails.data.PersonDetails
import com.beti1205.movieapp.feature.persondetails.data.PersonDetailsService
import com.beti1205.movieapp.utils.transformImageUrl
import javax.inject.Inject

interface FetchPersonDetailsUseCase {

    suspend operator fun invoke(
        personId: Int
    ): Result<PersonDetails>
}

class FetchPersonDetailsUseCaseImpl @Inject constructor(
    private val personDetailsService: PersonDetailsService,
    private val appConfig: AppConfig
) : FetchPersonDetailsUseCase {

    override suspend fun invoke(personId: Int): Result<PersonDetails> {
        return performRequest {
            personDetailsService.getPersonDetails(personId)
        }.flatMap { result ->
            Result.Success(
                result.copy(
                    personPoster = transformImageUrl(result.personPoster, appConfig.imageUrl)
                )
            )
        }
    }
}

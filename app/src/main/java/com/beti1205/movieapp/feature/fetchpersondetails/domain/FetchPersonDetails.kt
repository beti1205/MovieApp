package com.beti1205.movieapp.feature.fetchpersondetails.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetailsService
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
            personDetailsService.getPersonDetails(personId, appConfig.apiKey)
        }
    }
}

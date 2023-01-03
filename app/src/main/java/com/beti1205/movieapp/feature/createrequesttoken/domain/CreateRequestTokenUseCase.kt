package com.beti1205.movieapp.feature.createrequesttoken.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.createrequesttoken.data.RequestTokenResponse
import com.beti1205.movieapp.feature.createrequesttoken.data.RequestTokenService
import javax.inject.Inject

interface CreateRequestTokenUseCase {

    suspend operator fun invoke(): Result<RequestTokenResponse>
}

class CreateRequestTokenUseCaseImpl @Inject constructor(
    private val requestTokenService: RequestTokenService,
    private val appConfig: AppConfig
) : CreateRequestTokenUseCase {

    override suspend fun invoke(): Result<RequestTokenResponse> {
        return performRequest {
            requestTokenService.getRequestTokenResponse(appConfig.apiKey)
        }
    }
}

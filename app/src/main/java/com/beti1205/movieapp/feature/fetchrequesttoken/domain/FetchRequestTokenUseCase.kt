package com.beti1205.movieapp.feature.fetchrequesttoken.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.fetchrequesttoken.data.RequestTokenResponse
import com.beti1205.movieapp.feature.fetchrequesttoken.data.RequestTokenService
import javax.inject.Inject

interface FetchRequestTokenUseCase {

    suspend operator fun invoke(): Result<RequestTokenResponse>
}

class FetchRequestTokenUseCaseImpl @Inject constructor(
    private val requestTokenService: RequestTokenService,
    private val appConfig: AppConfig
) : FetchRequestTokenUseCase {

    override suspend fun invoke(): Result<RequestTokenResponse> {
        return performRequest {
            requestTokenService.getRequestTokenResponse(appConfig.apiKey)
        }
    }
}

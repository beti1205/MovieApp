package com.beti1205.movieapp.feature.session.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.session.data.CreateSessionBody
import com.beti1205.movieapp.feature.session.data.SessionResponse
import com.beti1205.movieapp.feature.session.data.SessionService
import javax.inject.Inject

interface CreateSessionUseCase {

    suspend operator fun invoke(token: String): Result<SessionResponse>
}

class CreateSessionUseCaseImpl @Inject constructor(
    private val sessionService: SessionService,
    private val appConfig: AppConfig
) : CreateSessionUseCase {

    override suspend fun invoke(token: String): Result<SessionResponse> {
        return performRequest {
            sessionService.createSession(appConfig.apiKey, CreateSessionBody(token))
        }
    }
}

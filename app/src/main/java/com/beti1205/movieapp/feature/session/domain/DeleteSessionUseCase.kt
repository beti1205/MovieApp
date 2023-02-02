/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.session.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.GenericApiException
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.session.data.DeleteSessionBody
import com.beti1205.movieapp.feature.session.data.DeleteSessionService
import javax.inject.Inject

interface DeleteSessionUseCase {

    suspend operator fun invoke(): Result<Unit>
}

class DeleteSessionUseCaseImpl @Inject constructor(
    private val deleteSessionService: DeleteSessionService,
    private val appConfig: AppConfig,
    private val authManager: AuthManager
) : DeleteSessionUseCase {

    override suspend fun invoke(): Result<Unit> {
        val result = performRequest {
            deleteSessionService.deleteSession(
                appConfig.apiKey,
                DeleteSessionBody(authManager.sessionId!!)
            )
        }

        return when (result) {
            is Result.Success -> if (result.data.success) {
                authManager.setSessionId(null)
                Result.Success(Unit)
            } else {
                Result.Error(GenericApiException)
            }
            is Result.Error -> result
        }
    }
}

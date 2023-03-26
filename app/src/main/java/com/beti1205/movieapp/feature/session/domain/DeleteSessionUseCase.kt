/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.session.domain

import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.auth.AuthManager
import com.beti1205.movieapp.common.exceptions.GenericApiException
import com.beti1205.movieapp.common.flatMap
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.session.data.DeleteSessionBody
import com.beti1205.movieapp.feature.session.data.DeleteSessionService
import javax.inject.Inject

interface DeleteSessionUseCase {

    suspend operator fun invoke(): Result<Unit>
}

class DeleteSessionUseCaseImpl @Inject constructor(
    private val deleteSessionService: DeleteSessionService,
    private val authManager: AuthManager
) : DeleteSessionUseCase {

    override suspend fun invoke(): Result<Unit> {
        return performRequest {
            deleteSessionService.deleteSession(
                DeleteSessionBody(authManager.sessionId!!)
            )
        }.flatMap { result ->
            if (result.success) {
                authManager.setSessionId(null)
                Result.Success(Unit)
            } else {
                Result.Error(GenericApiException)
            }
        }
    }
}

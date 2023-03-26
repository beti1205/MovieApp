/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.token.domain

import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.token.data.RequestTokenResponse
import com.beti1205.movieapp.feature.token.data.RequestTokenService
import javax.inject.Inject

interface RequestTokenUseCase {

    suspend operator fun invoke(): Result<RequestTokenResponse>
}

class RequestTokenUseCaseImpl @Inject constructor(
    private val requestTokenService: RequestTokenService
) : RequestTokenUseCase {

    override suspend fun invoke(): Result<RequestTokenResponse> {
        return performRequest {
            requestTokenService.getRequestTokenResponse()
        }
    }
}

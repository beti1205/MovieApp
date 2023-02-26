/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.accountdetails.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.flatMap
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetailsService
import com.beti1205.movieapp.feature.accountdetails.data.Tmdb
import com.beti1205.movieapp.feature.transformavatarurl.TransformAvatarUrlUseCase
import javax.inject.Inject

interface FetchAccountDetailsUseCase {

    suspend operator fun invoke(): Result<AccountDetails>
}

class FetchAccountDetailsUseCaseImpl @Inject constructor(
    private val accountDetailsService: AccountDetailsService,
    private val appConfig: AppConfig,
    private val authManager: AuthManager,
    private val transformAvatarUrlUseCase: TransformAvatarUrlUseCase
) : FetchAccountDetailsUseCase {
    override suspend fun invoke(): Result<AccountDetails> {
        val result = performRequest {
            accountDetailsService.getAccountDetails(
                key = appConfig.apiKey,
                session = authManager.sessionId!!
            )
        }.flatMap { result ->
            Result.Success(
                result.copy(
                    avatar = result.avatar.copy(
                        tmdb = Tmdb(transformAvatarUrlUseCase(avatarUrl = result.avatar.tmdb.avatarPath))
                    )
                )
            )
        }

        when (result) {
            is Result.Success -> {
                authManager.setAccountId(result.data.id)
            }
            is Result.Error -> {}
        }
        return result
    }
}

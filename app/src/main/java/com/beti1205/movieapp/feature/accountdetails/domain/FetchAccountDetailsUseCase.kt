/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.accountdetails.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.auth.AuthManager
import com.beti1205.movieapp.common.flatMap
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetailsService
import com.beti1205.movieapp.feature.accountdetails.data.Tmdb
import com.beti1205.movieapp.utils.transformAvatarUrl
import javax.inject.Inject

interface FetchAccountDetailsUseCase {

    suspend operator fun invoke(): Result<AccountDetails>
}

class FetchAccountDetailsUseCaseImpl @Inject constructor(
    private val accountDetailsService: AccountDetailsService,
    private val appConfig: AppConfig,
    private val authManager: AuthManager
) : FetchAccountDetailsUseCase {
    override suspend fun invoke(): Result<AccountDetails> {
        return performRequest {
            accountDetailsService.getAccountDetails(
                key = appConfig.apiKey,
                session = authManager.sessionId!!
            )
        }.flatMap { result ->
            authManager.setAccountId(result.id)

            Result.Success(
                result.copy(
                    avatar = result.avatar.copy(
                        tmdb = Tmdb(
                            avatarPath = transformAvatarUrl(
                                avatarUrl = result.avatar.tmdb.avatarPath,
                                imageUrl = appConfig.imageUrl
                            )
                        )
                    )
                )
            )
        }
    }
}

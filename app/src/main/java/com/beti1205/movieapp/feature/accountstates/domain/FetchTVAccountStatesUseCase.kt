/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.accountstates.domain

import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.auth.AuthManager
import com.beti1205.movieapp.common.exceptions.NotLoggedInException
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.accountstates.data.AccountStates
import com.beti1205.movieapp.feature.accountstates.data.AccountStatesService
import javax.inject.Inject

interface FetchTVAccountStatesUseCase {

    suspend operator fun invoke(tvId: Int): Result<AccountStates>
}

class FetchTVAccountStatesUseCaseImpl @Inject constructor(
    private val accountStatesService: AccountStatesService,
    private val authManager: AuthManager
) : FetchTVAccountStatesUseCase {

    override suspend fun invoke(tvId: Int): Result<AccountStates> {
        if (!authManager.isLoggedIn) {
            return Result.Error(NotLoggedInException)
        }
        return performRequest {
            accountStatesService.getTVAccountStates(
                tvId = tvId
            )
        }
    }
}

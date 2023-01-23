package com.beti1205.movieapp.feature.fetchaccountstates.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.deletesession.domain.GenericApiException
import com.beti1205.movieapp.feature.fetchaccountstates.data.AccountStates
import com.beti1205.movieapp.feature.fetchaccountstates.data.AccountStatesService
import javax.inject.Inject

interface FetchTVAccountStatesUseCase {

    suspend operator fun invoke(tvId: Int): Result<AccountStates>
}

class FetchTVAccountStatesUseCaseImpl @Inject constructor(
    private val accountStatesService: AccountStatesService,
    private val appConfig: AppConfig,
    private val authManager: AuthManager
) : FetchTVAccountStatesUseCase {

    override suspend fun invoke(tvId: Int): Result<AccountStates> {
        if (!authManager.isLoggedIn) {
            return Result.Error(GenericApiException)
        }
        return performRequest {
            accountStatesService.getTVAccountStates(
                tvId = tvId,
                key = appConfig.apiKey,
                sessionId = authManager.sessionId!!
            )
        }
    }
}

package com.beti1205.movieapp.feature.fetchaccountstates.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.fetchaccountstates.data.AccountStates
import com.beti1205.movieapp.feature.fetchaccountstates.data.AccountStatesService
import javax.inject.Inject

interface FetchAccountStatesUseCase {

    suspend operator fun invoke(movieId: Int): Result<AccountStates>
}

class FetchAccountStatesUseCaseImpl @Inject constructor(
    private val accountStatesService: AccountStatesService,
    private val appConfig: AppConfig,
    private val authManager: AuthManager
) : FetchAccountStatesUseCase {
    override suspend fun invoke(movieId: Int): Result<AccountStates> {
        if (!authManager.isLoggedIn) {
            return Result.Success(
                AccountStates(
                    id = -1,
                    favorite = false,
                    watchlist = false
                )
            )
        }
        return performRequest {
            accountStatesService.getAccountStates(
                movieId = movieId,
                key = appConfig.apiKey,
                authManager.sessionId!!
            )
        }
    }
}

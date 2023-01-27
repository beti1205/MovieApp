package com.beti1205.movieapp.feature.accountstates.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.accountstates.data.AccountStates
import com.beti1205.movieapp.feature.accountstates.data.AccountStatesService
import javax.inject.Inject

interface FetchMoviesAccountStatesUseCase {

    suspend operator fun invoke(movieId: Int): Result<AccountStates>
}

class FetchMoviesAccountStatesUseCaseImpl @Inject constructor(
    private val accountStatesService: AccountStatesService,
    private val appConfig: AppConfig,
    private val authManager: AuthManager
) : FetchMoviesAccountStatesUseCase {
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
            accountStatesService.getMoviesAccountStates(
                movieId = movieId,
                key = appConfig.apiKey,
                authManager.sessionId!!
            )
        }
    }
}

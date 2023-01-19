package com.beti1205.movieapp.feature.fetchaccountdetails.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.fetchaccountdetails.data.AccountDetails
import com.beti1205.movieapp.feature.fetchaccountdetails.data.AccountDetailsService
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
        val result = performRequest {
            accountDetailsService.getAccountDetails(
                key = appConfig.apiKey,
                session = authManager.sessionId!!
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

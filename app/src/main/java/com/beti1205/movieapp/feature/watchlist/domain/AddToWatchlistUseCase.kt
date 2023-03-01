/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.watchlist.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.MediaType
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.watchlist.data.AddToWatchlistBody
import com.beti1205.movieapp.feature.watchlist.data.AddToWatchlistService
import javax.inject.Inject

interface AddToWatchlistUseCase {

    suspend operator fun invoke(
        watchlist: Boolean,
        mediaType: MediaType,
        mediaId: Int
    ): Result<Unit>
}

class AddToWatchlistUseCaseImpl @Inject constructor(
    private val addToWatchlistService: AddToWatchlistService,
    private val appConfig: AppConfig,
    private val authManager: AuthManager
) : AddToWatchlistUseCase {
    override suspend fun invoke(
        watchlist: Boolean,
        mediaType: MediaType,
        mediaId: Int
    ): Result<Unit> {
        val result =
            performRequest {
                addToWatchlistService.addToWatchlist(
                    accountId = authManager.accountId,
                    key = appConfig.apiKey,
                    sessionId = authManager.sessionId!!,
                    body = AddToWatchlistBody(
                        watchlist = watchlist,
                        mediaType = mediaType.mediaType,
                        mediaId = mediaId
                    )
                )
            }

        return when (result) {
            is Result.Error -> result
            is Result.Success -> Result.Success(Unit)
        }
    }
}

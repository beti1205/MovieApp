/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.favorite.domain

import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.auth.AuthManager
import com.beti1205.movieapp.common.data.MediaType
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.favorite.data.MarkFavoriteBody
import com.beti1205.movieapp.feature.favorite.data.MarkFavoriteService
import javax.inject.Inject

interface MarkFavoriteUseCase {

    suspend operator fun invoke(
        favorite: Boolean,
        mediaType: MediaType,
        mediaId: Int
    ): Result<Unit>
}

class MarkFavoriteUseCaseImpl @Inject constructor(
    private val markFavoriteService: MarkFavoriteService,
    private val authManager: AuthManager
) : MarkFavoriteUseCase {

    override suspend fun invoke(
        favorite: Boolean,
        mediaType: MediaType,
        mediaId: Int
    ): Result<Unit> {
        return performRequest {
            markFavoriteService.markFavorite(
                accountId = authManager.accountId,
                body = MarkFavoriteBody(
                    favorite = favorite,
                    mediaType = mediaType.mediaType,
                    mediaId = mediaId
                )
            )
        }
    }
}

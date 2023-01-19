package com.beti1205.movieapp.feature.markfavorite.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.markfavorite.data.MarkFavoriteBody
import com.beti1205.movieapp.feature.markfavorite.data.MarkFavoriteResponse
import com.beti1205.movieapp.feature.markfavorite.data.MarkFavoriteService
import javax.inject.Inject

enum class Media(val mediaType: String) {
    MOVIE("movie"),
    TV("tv")
}

interface MarkFavoriteUseCase {

    suspend operator fun invoke(
        favorite: Boolean,
        mediaType: Media,
        mediaId: Int
    ): Result<MarkFavoriteResponse>
}

class MarkFavoriteUseCaseImpl @Inject constructor(
    private val markFavoriteService: MarkFavoriteService,
    private val appConfig: AppConfig,
    private val authManager: AuthManager
) : MarkFavoriteUseCase {

    override suspend fun invoke(
        favorite: Boolean,
        mediaType: Media,
        mediaId: Int
    ): Result<MarkFavoriteResponse> {
        return performRequest {
            markFavoriteService.markFavorite(
                accountId = authManager.accountId,
                key = appConfig.apiKey,
                sessionId = authManager.sessionId!!,
                body = MarkFavoriteBody(
                    favorite = favorite,
                    mediaType = mediaType.mediaType,
                    mediaId = mediaId
                )
            )
        }
    }
}

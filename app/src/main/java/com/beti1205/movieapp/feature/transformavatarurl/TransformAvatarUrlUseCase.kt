/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.transformavatarurl

import com.beti1205.movieapp.common.AppConfig
import javax.inject.Inject

interface TransformAvatarUrlUseCase {

    operator fun invoke(
        avatarUrl: String?
    ): String?
}

class TransformAvatarUrlUseCaseImpl @Inject constructor(
    val appConfig: AppConfig
) : TransformAvatarUrlUseCase {
    override fun invoke(avatarUrl: String?): String? {
        return when {
            avatarUrl == null -> null
            avatarUrl.startsWith("/http") -> avatarUrl.removePrefix("/")
            else -> "${appConfig.imageUrl}$avatarUrl"
        }
    }
}

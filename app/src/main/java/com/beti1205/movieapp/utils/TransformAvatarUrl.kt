/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.utils

fun transformAvatarUrl(avatarUrl: String?, imageUrl: String): String? {
    return when {
        avatarUrl == null -> null
        avatarUrl.startsWith("/http") -> avatarUrl.removePrefix("/")
        else -> "${imageUrl}$avatarUrl"
    }
}

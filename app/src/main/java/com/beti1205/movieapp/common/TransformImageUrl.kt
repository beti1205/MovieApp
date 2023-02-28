/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.common

fun transformImageUrl(posterPath: String?, imageUrl: String): String? {
    return when {
        posterPath != null -> "${imageUrl}$posterPath"
        else -> null
    }
}

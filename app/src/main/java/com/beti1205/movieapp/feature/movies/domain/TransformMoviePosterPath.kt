/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.movies.domain

import com.beti1205.movieapp.common.ApiResponse
import com.beti1205.movieapp.feature.movies.data.Movie

fun ApiResponse<Movie>.transformMoviePosterPath(imageUrl: String) =
    this.copy(
        items = this.items.map { movie ->
            val poster = when {
                movie.posterPath != null -> "${imageUrl}${movie.posterPath}"
                else -> null
            }
            movie.copy(
                posterPath = poster
            )
        }
    )

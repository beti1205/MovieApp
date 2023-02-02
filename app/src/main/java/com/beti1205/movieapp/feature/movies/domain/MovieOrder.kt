/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.movies.domain

enum class MovieOrder {
    POPULAR,
    TOP_RATED,
    UPCOMING,
    NOW_PLAYING;

    companion object {
        fun from(value: Int): MovieOrder {
            if (value == -1) {
                return POPULAR
            }

            return values().find { it.ordinal == value } ?: POPULAR
        }

        fun availableValues(): List<MovieOrder> = values().toList()
    }
}

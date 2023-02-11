/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.movies.domain

enum class FavoriteListOrder(val type: String) {
    OLDEST("created_at.asc"),
    LATEST("created_at.desc");

    companion object {
        fun from(value: Int): FavoriteListOrder {
            if (value == -1) {
                return OLDEST
            }

            return FavoriteListOrder.values().find { it.ordinal == value }
                ?: OLDEST
        }

        fun availableValues(): List<FavoriteListOrder> = values().toList()
    }
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.common.data

enum class ListOrder(val type: String) {
    OLDEST("created_at.asc"),
    LATEST("created_at.desc");

    companion object {
        fun from(value: Int): ListOrder {
            if (value == -1) {
                return OLDEST
            }

            return ListOrder.values().find { it.ordinal == value } ?: OLDEST
        }

        fun availableValues(): List<ListOrder> = values().toList()
    }
}

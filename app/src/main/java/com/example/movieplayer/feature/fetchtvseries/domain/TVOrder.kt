package com.example.movieplayer.feature.fetchtvseries.domain

enum class TVOrder {
    POPULAR,
    TOP_RATED,
    AIRING_TODAY,
    ON_THE_AIR;

    companion object {
        fun from(value: Int): TVOrder {
            if (value == -1) {
                return POPULAR
            }

            return values().find { it.ordinal == value } ?: POPULAR
        }
    }
}

package com.beti1205.movieapp.feature.fetchmovies.domain

enum class MovieOrder {
    POPULAR,
    UPCOMING,
    TOP_RATED,
    NOW_PLAYING;

    companion object {
        fun from(value: Int): MovieOrder {
            if (value == -1) {
                return POPULAR
            }

            return values().find { it.ordinal == value } ?: POPULAR
        }
    }
}

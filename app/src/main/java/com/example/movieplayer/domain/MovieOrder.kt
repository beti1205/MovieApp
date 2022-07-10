package com.example.movieplayer.domain

enum class MovieOrder {
    POPULAR,
    UPCOMING,
    TOP_RATED,
    NOW_PLAYING,;


    companion object {
        fun from(value: Int?): MovieOrder {
            if(value == null) {
                return POPULAR
            }

            return values().find { it.ordinal == value } ?: POPULAR
        }
    }
}
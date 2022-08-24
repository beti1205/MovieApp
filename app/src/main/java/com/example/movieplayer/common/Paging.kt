package com.example.movieplayer.common

fun <T> ApiResponse<T>.getNextPageKey(): Int? = when {
    page == null || page == 0 -> null
    totalPages == null || totalPages == 0 -> null
    page + 1 <= totalPages -> page + 1
    else -> null
}

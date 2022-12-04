package com.beti1205.movieapp.common

sealed class Result<out T : Any?> {
    data class Success<out T : Any?>(val data: T) : Result<T>()
    data class Error(val message: Throwable?) : Result<Nothing>()
}

inline fun <T : Any?> performRequest(block: () -> T): Result<T> {
    return try {
        Result.Success(block())
    } catch (ex: Exception) {
        Result.Error(ex)
    }
}

inline fun <T, U> Result<T>.flatMap(f: (T) -> Result<U>): Result<U> = when (this) {
    is Result.Success<T> -> f(data)
    is Result.Error -> this
}

inline fun <T1, T2, U> flatZip(
    r1: Result<T1>,
    r2: Result<T2>,
    transform: (T1, T2) -> Result<U>
): Result<U> =
    r1.flatMap { v1 ->
        r2.flatMap { v2 ->
            transform(v1, v2)
        }
    }

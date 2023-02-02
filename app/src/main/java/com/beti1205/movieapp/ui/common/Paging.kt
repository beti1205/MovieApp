/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common

import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.compose.LazyPagingItems
import com.beti1205.movieapp.common.ApiResponse

fun <T> ApiResponse<T>.getNextPageKey(): Int? = when {
    page == null || page == 0 -> null
    totalPages == null || totalPages == 0 -> null
    page + 1 <= totalPages -> page + 1
    else -> null
}

fun CombinedLoadStates.getErrorState(): LoadState.Error? = when {
    prepend is LoadState.Error -> prepend as LoadState.Error
    append is LoadState.Error -> append as LoadState.Error
    refresh is LoadState.Error -> refresh as LoadState.Error
    else -> null
}

fun <T : Any> LazyPagingItems<T>.isQueryTooShort(): Boolean {
    return (loadState.source.refresh as? LoadState.Error)?.error == TooShortQueryException
}

fun <T : Any> LazyPagingItems<T>.isListEmpty(): Boolean {
    return loadState.refresh is LoadState.NotLoading && itemCount == 0
}

fun <T : Any> LazyPagingItems<T>.hasError(): Boolean {
    return loadState.source.refresh is LoadState.Error
}

fun <T : Any> LazyPagingItems<T>.isLoading(): Boolean {
    return loadState.source.refresh is LoadState.Loading
}

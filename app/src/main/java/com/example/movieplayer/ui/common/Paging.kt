package com.example.movieplayer.ui.common

import androidx.core.view.isVisible
import androidx.paging.CombinedLoadStates
import androidx.paging.LoadState
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.movieplayer.common.ApiResponse
import com.example.movieplayer.databinding.SearchListBinding

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

fun <T : Any, VH : RecyclerView.ViewHolder> SearchListBinding.updateState(
    loadStates: CombinedLoadStates,
    adapter: PagingDataAdapter<T, VH>
) {
    val isLoading = loadStates.source.refresh is LoadState.Loading
    val hasError = loadStates.source.refresh is LoadState.Error
    val isListEmpty = loadStates.isListEmpty(adapter)
    val tooShortQuery = loadStates.isQueryTooShort()

    searchRecyclerView.isVisible = !isLoading && !hasError
    searchProgressBar.isVisible = isLoading && !tooShortQuery
    connectionError.isVisible = hasError && !tooShortQuery
    searchEmptyStateMessage.isVisible = tooShortQuery
    searchEmptyListMessage.isVisible = isListEmpty && !tooShortQuery
}

private fun <T : Any, VH : RecyclerView.ViewHolder> CombinedLoadStates.isListEmpty(
    adapter: PagingDataAdapter<T, VH>
) = refresh is LoadState.NotLoading && adapter.itemCount == 0

private fun CombinedLoadStates.isQueryTooShort(): Boolean {
    return (source.refresh as? LoadState.Error)?.error == TooShortQueryException
}

package com.beti1205.movieapp.ui.tvseries.list.widget

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries
import com.beti1205.movieapp.ui.common.getErrorState
import com.beti1205.movieapp.ui.movies.common.widget.PagingError

@Composable
fun TVSeriesListError(tvSeriesListItems: LazyPagingItems<TVSeries>) {
    if (tvSeriesListItems.loadState.getErrorState() != null) {
        PagingError(onRetryClick = { tvSeriesListItems.retry() })
    }
}

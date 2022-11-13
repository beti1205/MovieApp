package com.beti1205.movieapp.ui.tvseries.search.widget

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries
import com.beti1205.movieapp.ui.common.hasError
import com.beti1205.movieapp.ui.common.isListEmpty
import com.beti1205.movieapp.ui.common.isLoading
import com.beti1205.movieapp.ui.common.isQueryTooShort
import com.beti1205.movieapp.ui.common.widget.SearchEmptyList
import com.beti1205.movieapp.ui.common.widget.SearchEmptyState
import com.beti1205.movieapp.ui.tvseries.common.widget.TVSeriesList

@Composable
fun SearchTVSeriesList(
    searchTVSeriesItems: LazyPagingItems<TVSeries>,
    onTVSeriesClicked: (TVSeries) -> Unit
) {
    searchTVSeriesItems.apply {
        when {
            isQueryTooShort() -> SearchEmptyState()
            isListEmpty() -> SearchEmptyList()
            hasError() -> SearchTVSeriesPagingError(items = searchTVSeriesItems)
            isLoading() -> CircularProgressIndicator()
            else -> TVSeriesList(
                tvSeriesListItems = searchTVSeriesItems,
                onTVSeriesClicked = onTVSeriesClicked
            )
        }
    }
}

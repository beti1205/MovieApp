/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.ui.common.widget.search.SearchTopAppBar
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.tvseries.common.PagingTVSeriesPreviewDataProvider
import com.beti1205.movieapp.ui.tvseries.search.widget.SearchTVSeriesList
import kotlinx.coroutines.flow.flowOf

@Composable
fun SearchTVSeriesScreen(
    viewModel: SearchTVSeriesViewModel = hiltViewModel(),
    onTVSeriesClicked: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val searchTVSeriesItems = viewModel.querySearchResults.collectAsLazyPagingItems()
    val query by viewModel.queryFlow.collectAsState()

    SearchTVSeriesScreen(
        query = query,
        searchTVSeriesItems = searchTVSeriesItems,
        onTVSeriesClicked = onTVSeriesClicked,
        onQueryChange = viewModel::onQueryChanged,
        onBackPressed = onBackPressed
    )
}

@Composable
fun SearchTVSeriesScreen(
    query: String,
    searchTVSeriesItems: LazyPagingItems<TVSeries>,
    onTVSeriesClicked: (Int) -> Unit,
    onQueryChange: (String) -> Unit,
    onBackPressed: () -> Unit
) {
    MovieAppTheme {
        Scaffold(
            topBar = {
                SearchTopAppBar(
                    query = query,
                    onQueryChange = onQueryChange,
                    onBackPressed = onBackPressed
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                SearchTVSeriesList(
                    searchTVSeriesItems = searchTVSeriesItems,
                    onTVSeriesClicked = onTVSeriesClicked
                )
            }
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SearchTVSeriesScreenPreview() {
    val items = flowOf(PagingTVSeriesPreviewDataProvider.pagingData).collectAsLazyPagingItems()
    MovieAppTheme {
        SearchTVSeriesScreen(
            query = "test",
            searchTVSeriesItems = items,
            onTVSeriesClicked = {},
            onQueryChange = {},
            onBackPressed = {}
        )
    }
}

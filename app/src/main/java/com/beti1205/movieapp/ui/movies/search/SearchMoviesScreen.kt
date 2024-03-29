/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
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
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.ui.common.widget.search.SearchTopAppBar
import com.beti1205.movieapp.ui.movies.common.PagingMoviePreviewDataProvider
import com.beti1205.movieapp.ui.movies.search.widget.SearchMoviesList
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun SearchMoviesScreen(
    viewModel: SearchMoviesViewModel = hiltViewModel(),
    onMovieClicked: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val searchMoviesItems = viewModel.querySearchResults.collectAsLazyPagingItems()
    val query by viewModel.queryFlow.collectAsState()

    SearchMoviesScreen(
        query = query,
        searchMoviesItems = searchMoviesItems,
        onMovieClicked = onMovieClicked,
        onQueryChange = viewModel::onQueryChanged,
        onBackPressed = onBackPressed
    )
}

@Composable
fun SearchMoviesScreen(
    query: String,
    searchMoviesItems: LazyPagingItems<Movie>,
    onMovieClicked: (Int) -> Unit,
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
            Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                SearchMoviesList(
                    searchMoviesItems = searchMoviesItems,
                    onMovieClicked = onMovieClicked
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
fun SearchMoviesScreenPreview() {
    val items = flowOf(PagingMoviePreviewDataProvider.pagingData).collectAsLazyPagingItems()
    SearchMoviesScreen(
        query = "",
        searchMoviesItems = items,
        onMovieClicked = {},
        onQueryChange = {},
        onBackPressed = {}
    )
}

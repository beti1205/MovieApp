/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.feature.movies.domain.MovieOrder
import com.beti1205.movieapp.ui.common.widget.list.ListTopAppBar
import com.beti1205.movieapp.ui.movies.common.PagingMoviePreviewDataProvider
import com.beti1205.movieapp.ui.movies.common.widget.MovieList
import com.beti1205.movieapp.ui.movies.list.widget.MovieListError
import com.beti1205.movieapp.ui.movies.list.widget.MovieOrderChipGroup
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieScreen(
    viewModel: MovieViewModel,
    onMovieClicked: (Int) -> Unit,
    onSearchClicked: () -> Unit
) {
    val movieListItems = viewModel.movies.collectAsLazyPagingItems()
    val selectedMovieOrder by viewModel.order.collectAsState()

    MovieScreen(
        movieListItems = movieListItems,
        selectedMovieOrder = selectedMovieOrder,
        onSelectedMovieOrderChanged = viewModel::onOrderChanged,
        onMovieClicked = onMovieClicked,
        onSearchClicked = onSearchClicked
    )
}

@Composable
fun MovieScreen(
    movieListItems: LazyPagingItems<Movie>,
    selectedMovieOrder: MovieOrder,
    onSelectedMovieOrderChanged: (MovieOrder) -> Unit,
    onMovieClicked: (Int) -> Unit,
    onSearchClicked: () -> Unit
) {
    MovieAppTheme {
        Scaffold(topBar = {
            ListTopAppBar(
                stringResource(id = R.string.movies_title),
                onSearchClicked
            )
        }) { paddingValues ->
            Column(modifier = Modifier.fillMaxSize().padding(paddingValues)) {
                MovieOrderChipGroup(
                    selectedMovieOrder = selectedMovieOrder,
                    onSelectedMovieOrderChanged = onSelectedMovieOrderChanged
                )
                MovieList(
                    movieListItems = movieListItems,
                    onMovieClicked = onMovieClicked,
                    modifier = Modifier.weight(1f)
                )
                MovieListError(movieListItems = movieListItems)
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
fun MovieScreenPreview() {
    val items = flowOf(PagingMoviePreviewDataProvider.pagingData).collectAsLazyPagingItems()
    MovieScreen(
        movieListItems = items,
        selectedMovieOrder = MovieOrder.POPULAR,
        onSelectedMovieOrderChanged = {},
        onMovieClicked = {},
        onSearchClicked = {}
    )
}

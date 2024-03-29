/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.common.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.ui.common.widget.list.items
import com.beti1205.movieapp.ui.movies.common.PagingMoviePreviewDataProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieList(
    movieListItems: LazyPagingItems<Movie>,
    onMovieClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier
            .padding(horizontal = 8.dp)
    ) {
        items(items = movieListItems) { item ->
            if (item != null) {
                MovieItem(
                    movie = item,
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
fun MovieListPreview() {
    val items = flowOf(PagingMoviePreviewDataProvider.pagingData).collectAsLazyPagingItems()

    MovieAppTheme {
        MovieList(movieListItems = items, onMovieClicked = {})
    }
}

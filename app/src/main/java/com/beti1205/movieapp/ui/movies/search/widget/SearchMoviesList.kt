/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.search.widget

import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.ui.common.hasError
import com.beti1205.movieapp.ui.common.isListEmpty
import com.beti1205.movieapp.ui.common.isLoading
import com.beti1205.movieapp.ui.common.isQueryTooShort
import com.beti1205.movieapp.ui.common.widget.Loader
import com.beti1205.movieapp.ui.common.widget.search.SearchEmptyList
import com.beti1205.movieapp.ui.common.widget.search.SearchEmptyState
import com.beti1205.movieapp.ui.movies.common.widget.MovieList

@Composable
fun SearchMoviesList(
    searchMoviesItems: LazyPagingItems<Movie>,
    onMovieClicked: (Int) -> Unit
) {
    searchMoviesItems.apply {
        when {
            isQueryTooShort() -> SearchEmptyState()
            isListEmpty() -> SearchEmptyList()
            hasError() -> SearchMoviesPagingError(onRetryClick = { retry() })
            isLoading() -> Loader()
            else -> MovieList(
                movieListItems = searchMoviesItems,
                onMovieClicked = onMovieClicked
            )
        }
    }
}

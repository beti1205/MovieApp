package com.beti1205.movieapp.ui.movies.search.widget

import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.paging.compose.LazyPagingItems
import com.beti1205.movieapp.feature.fetchmovies.data.Movie
import com.beti1205.movieapp.ui.common.hasError
import com.beti1205.movieapp.ui.common.isListEmpty
import com.beti1205.movieapp.ui.common.isLoading
import com.beti1205.movieapp.ui.common.isQueryTooShort
import com.beti1205.movieapp.ui.common.widget.SearchEmptyList
import com.beti1205.movieapp.ui.common.widget.SearchEmptyState
import com.beti1205.movieapp.ui.movies.common.widget.MovieList

@Composable
fun SearchMoviesList(
    searchMoviesItems: LazyPagingItems<Movie>,
    onMovieClicked: (Movie) -> Unit
) {
    searchMoviesItems.apply {
        when {
            isQueryTooShort() -> SearchEmptyState()
            isListEmpty() -> SearchEmptyList()
            hasError() -> SearchMoviesPagingError(items = searchMoviesItems)
            isLoading() -> CircularProgressIndicator()
            else -> MovieList(
                movieListItems = searchMoviesItems,
                onMovieClicked = onMovieClicked
            )
        }
    }
}

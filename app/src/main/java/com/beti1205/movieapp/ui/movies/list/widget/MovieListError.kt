package com.beti1205.movieapp.ui.movies.list.widget

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.ui.common.getErrorState
import com.beti1205.movieapp.ui.movies.common.PagingMoviePreviewDataProvider
import com.beti1205.movieapp.ui.movies.common.widget.PagingError
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieListError(movieListItems: LazyPagingItems<Movie>) {
    AnimatedVisibility(visible = movieListItems.loadState.getErrorState() != null) {
        PagingError(
            onRetryClick = { movieListItems.retry() }
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun MovieListError() {
    val items = flowOf(PagingMoviePreviewDataProvider.pagingData).collectAsLazyPagingItems()
    MovieListError(movieListItems = items)
}

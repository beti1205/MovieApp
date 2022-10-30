package com.beti1205.movieapp.ui.movies.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.beti1205.movieapp.feature.fetchmovies.data.Movie
import com.beti1205.movieapp.feature.fetchmovies.domain.MovieOrder
import com.beti1205.movieapp.ui.movies.common.MoviePreviewDataProvider
import com.beti1205.movieapp.ui.movies.common.widget.MovieList
import com.beti1205.movieapp.ui.movies.list.widget.MovieListError
import com.beti1205.movieapp.ui.movies.list.widget.MovieOrderChipGroup
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun MovieScreen(
    viewModel: MovieViewModel,
    onMovieClicked: (Movie) -> Unit
) {
    val movieListItems = viewModel.movies.collectAsLazyPagingItems()
    val selectedMovieOrder by viewModel.order.collectAsState()

    MovieScreen(
        movieListItems = movieListItems,
        selectedMovieOrder = selectedMovieOrder,
        onSelectedMovieOrderChanged = viewModel::onOrderChanged,
        onMovieClicked = onMovieClicked
    )
}

@Composable
fun MovieScreen(
    movieListItems: LazyPagingItems<Movie>,
    selectedMovieOrder: MovieOrder,
    onSelectedMovieOrderChanged: (MovieOrder) -> Unit,
    onMovieClicked: (Movie) -> Unit
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column {
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
    val items = flowOf(MoviePreviewDataProvider.pagingData).collectAsLazyPagingItems()
    MovieScreen(
        movieListItems = items,
        selectedMovieOrder = MovieOrder.POPULAR,
        onSelectedMovieOrderChanged = {},
        onMovieClicked = {}
    )
}

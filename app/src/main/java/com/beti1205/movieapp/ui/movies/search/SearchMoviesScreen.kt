package com.beti1205.movieapp.ui.movies.search

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.beti1205.movieapp.feature.fetchmovies.data.Movie
import com.beti1205.movieapp.ui.movies.common.PagingMoviePreviewDataProvider
import com.beti1205.movieapp.ui.movies.search.widget.SearchMoviesList
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import kotlinx.coroutines.flow.flowOf

@Composable
fun SearchMoviesScreen(
    viewModel: SearchMoviesViewModel,
    onMovieClicked: (Movie) -> Unit
) {
    val searchMoviesItems = viewModel.querySearchResults.collectAsLazyPagingItems()

    SearchMoviesScreen(
        searchMoviesItems = searchMoviesItems,
        onMovieClicked = onMovieClicked
    )
}

@Composable
fun SearchMoviesScreen(
    searchMoviesItems: LazyPagingItems<Movie>,
    onMovieClicked: (Movie) -> Unit
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            SearchMoviesList(
                searchMoviesItems = searchMoviesItems,
                onMovieClicked = onMovieClicked
            )
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
        searchMoviesItems = items,
        onMovieClicked = {}
    )
}

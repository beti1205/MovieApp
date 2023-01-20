package com.beti1205.movieapp.ui.movies.details

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.common.widget.Error
import com.beti1205.movieapp.ui.common.widget.Loader
import com.beti1205.movieapp.ui.common.widget.TopAppBar
import com.beti1205.movieapp.ui.movies.details.widget.MovieDetails
import com.beti1205.movieapp.ui.movies.details.widget.MovieDetailsScreenPreviewProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    onPersonClicked: (Int) -> Unit,
    onButtonClicked: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val state by viewModel.state.collectAsState()

    MovieDetailsScreen(
        state = state,
        onFavoriteClicked = viewModel::markFavorite,
        onPersonClicked = onPersonClicked,
        onButtonClicked = onButtonClicked,
        onBackPressed = onBackPressed
    )
}

@Composable
fun MovieDetailsScreen(
    state: MovieDetailsScreenState,
    onFavoriteClicked: (Boolean) -> Unit,
    onPersonClicked: (Int) -> Unit,
    onButtonClicked: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    MovieAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = stringResource(id = R.string.movie_details_label),
                    onBackPressed = onBackPressed
                )
            }
        ) { paddingValues ->
            Crossfade(
                targetState = state,
                modifier = Modifier.fillMaxSize().padding(paddingValues)
            ) { state ->
                when {
                    state.hasError -> Error()
                    state.isLoading -> Loader()
                    else -> MovieDetails(
                        movieDetails = state.movieDetails,
                        credits = state.credits,
                        onPersonClicked = onPersonClicked,
                        onButtonClicked = onButtonClicked,
                        onFavoriteClicked = onFavoriteClicked
                    )
                }
            }
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    heightDp = 2000
)
@Composable
fun MovieDetailsScreenPreview(
    @PreviewParameter(MovieDetailsScreenPreviewProvider::class)
    state: MovieDetailsScreenState
) {
    MovieAppTheme {
        MovieDetailsScreen(
            state = MovieDetailsScreenState(
                movieDetails = state.movieDetails,
                credits = state.credits,
                hasError = state.hasError
            ),
            onFavoriteClicked = {},
            onPersonClicked = {},
            onButtonClicked = {},
            onBackPressed = {}
        )
    }
}

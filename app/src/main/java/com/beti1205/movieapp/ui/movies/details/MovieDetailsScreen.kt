package com.beti1205.movieapp.ui.movies.details

import android.content.res.Configuration
import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.ui.common.widget.Loader
import com.beti1205.movieapp.ui.movies.details.widget.EmptyStateMessage
import com.beti1205.movieapp.ui.movies.details.widget.MovieDetails
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    onPersonClicked: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()

    MovieDetailsScreen(
        state = state,
        onPersonClicked = onPersonClicked
    )
}

@Composable
fun MovieDetailsScreen(
    state: MovieDetailsScreenState,
    onPersonClicked: (Int) -> Unit
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Crossfade(targetState = state) { state ->
                when {
                    state.hasError -> EmptyStateMessage()
                    state.isLoading -> Loader()
                    else -> MovieDetails(
                        movieDetails = state.movieDetails,
                        cast = state.credits?.cast,
                        crew = state.credits?.crew,
                        onPersonClicked = onPersonClicked
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
fun MovieDetailsScreenPreview() {
    MovieAppTheme {
        Surface {
            MovieDetailsScreen(
                state = MovieDetailsScreenState(
                    movieDetails = MovieDetailsPreviewDataProvider.movie,
                    credits = MovieDetailsPreviewDataProvider.credits,
                    hasError = false
                ),
                onPersonClicked = {}
            )
        }
    }
}

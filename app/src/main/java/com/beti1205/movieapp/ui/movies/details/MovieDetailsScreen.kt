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
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.beti1205.movieapp.ui.common.widget.EmptyStateMessage
import com.beti1205.movieapp.ui.common.widget.Loader
import com.beti1205.movieapp.ui.movies.details.widget.MovieDetails
import com.beti1205.movieapp.ui.movies.details.widget.MovieDetailsScreenPreviewProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    onPersonClicked: (Int) -> Unit,
    onButtonClicked: (Int) -> Unit
) {
    val state by viewModel.state.collectAsState()

    MovieDetailsScreen(
        state = state,
        onPersonClicked = onPersonClicked,
        onButtonClicked = onButtonClicked
    )
}

@Composable
fun MovieDetailsScreen(
    state: MovieDetailsScreenState,
    onPersonClicked: (Int) -> Unit,
    onButtonClicked: (Int) -> Unit
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Crossfade(targetState = state) { state ->
                when {
                    state.hasError -> EmptyStateMessage()
                    state.isLoading -> Loader()
                    else -> MovieDetails(
                        movieDetails = state.movieDetails,
                        credits = state.credits,
                        onPersonClicked = onPersonClicked,
                        onButtonClicked = onButtonClicked
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
        Surface {
            MovieDetailsScreen(
                state = MovieDetailsScreenState(
                    movieDetails = state.movieDetails,
                    credits = state.credits,
                    hasError = state.hasError
                ),
                onPersonClicked = {},
                onButtonClicked = {}
            )
        }
    }
}

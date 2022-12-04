package com.beti1205.movieapp.ui.movies.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.common.widget.Details
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.movies.details.widget.CastList
import com.beti1205.movieapp.ui.movies.details.widget.CrewList
import com.beti1205.movieapp.ui.movies.details.widget.EmptyStateMessage
import com.beti1205.movieapp.ui.movies.details.widget.SectionTitle
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
            if (state.hasError) {
                EmptyStateMessage()
            } else {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    state.movieDetails?.let { movieDetails ->
                        Details(
                            posterPath = movieDetails.posterPath,
                            title = movieDetails.title,
                            votes = movieDetails.votes,
                            releaseDate = movieDetails.releaseDate,
                            overview = movieDetails.overview,
                            genres = movieDetails.genres
                        )
                    }

                    StandardDivider()
                    SectionTitle(text = stringResource(id = R.string.cast))
                    CastList(
                        cast = state.credits?.cast,
                        onPersonClicked = onPersonClicked
                    )
                    StandardDivider()
                    SectionTitle(text = stringResource(id = R.string.crew))
                    CrewList(
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
    MovieDetailsScreen(
        state = MovieDetailsScreenState(
            movieDetails = MovieDetailsPreviewDataProvider.movie,
            credits = MovieDetailsPreviewDataProvider.credits,
            hasError = false
        ),
        onPersonClicked = {}
    )
}

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
import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.feature.fetchcredits.data.Crew
import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetails
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
    val movieDetails by viewModel.movieDetails.collectAsState()
    val cast by viewModel.cast.collectAsState()
    val crew by viewModel.crew.collectAsState()
    val hasError by viewModel.hasError.collectAsState()

    MovieDetailsScreen(
        movie = movieDetails,
        cast = cast,
        crew = crew,
        hasError = hasError,
        onPersonClicked = onPersonClicked
    )
}

@Composable
fun MovieDetailsScreen(
    movie: MovieDetails?,
    cast: List<Cast>?,
    crew: List<Crew>?,
    hasError: Boolean?,
    onPersonClicked: (Int) -> Unit
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            if (hasError == true) {
                EmptyStateMessage()
            } else {
                Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    if (movie != null) {
                        Details(
                            posterPath = movie.posterPath,
                            title = movie.title,
                            votes = movie.votes,
                            releaseDate = movie.releaseDate,
                            overview = movie.overview,
                            genres = movie.genres
                        )
                    }
                    StandardDivider()
                    SectionTitle(text = stringResource(id = R.string.cast))
                    CastList(
                        cast = cast,
                        onPersonClicked = onPersonClicked
                    )
                    StandardDivider()
                    SectionTitle(text = stringResource(id = R.string.crew))
                    CrewList(
                        crew = crew,
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
        movie = MovieDetailsPreviewDataProvider.movie,
        cast = MovieDetailsPreviewDataProvider.cast,
        crew = MovieDetailsPreviewDataProvider.crew,
        hasError = false,
        onPersonClicked = {}
    )
}

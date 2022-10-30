package com.beti1205.movieapp.ui.movies.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.feature.fetchcredits.data.Crew
import com.beti1205.movieapp.feature.fetchmovies.data.Movie
import com.beti1205.movieapp.ui.movies.common.MoviePreviewDataProvider
import com.beti1205.movieapp.ui.movies.details.widget.CastList
import com.beti1205.movieapp.ui.movies.details.widget.CrewList
import com.beti1205.movieapp.ui.movies.details.widget.EmptyStateMessage
import com.beti1205.movieapp.ui.movies.details.widget.MovieDetails
import com.beti1205.movieapp.ui.movies.details.widget.SectionTitle
import com.beti1205.movieapp.ui.movies.details.widget.StandardDivider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieDetailsScreen(viewModel: MovieDetailsViewModel) {
    val movie by viewModel.selectedMovie.observeAsState()
    val cast by viewModel.cast.observeAsState()
    val crew by viewModel.crew.observeAsState()
    val hasError by viewModel.hasError.observeAsState()

    MovieDetailsScreen(
        movie = movie,
        cast = cast,
        crew = crew,
        hasError = hasError
    )
}

@Composable
fun MovieDetailsScreen(movie: Movie?, cast: List<Cast>?, crew: List<Crew>?, hasError: Boolean?) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                MovieDetails(movie)
                StandardDivider()
                if (hasError == true) {
                    EmptyStateMessage()
                } else {
                    SectionTitle(text = stringResource(id = R.string.cast))
                    CastList(cast)
                    StandardDivider()
                    SectionTitle(text = stringResource(id = R.string.crew))
                    CrewList(crew)
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
        movie = MoviePreviewDataProvider.movie,
        cast = CreditsPreviewDataProvider.cast,
        crew = CreditsPreviewDataProvider.crew,
        hasError = false
    )
}

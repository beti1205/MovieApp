package com.beti1205.movieapp.ui.movies.details.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.feature.fetchcredits.data.Crew
import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetails
import com.beti1205.movieapp.ui.common.CreditsPreviewDataProvider
import com.beti1205.movieapp.ui.common.widget.credits.SectionCast
import com.beti1205.movieapp.ui.common.widget.credits.SectionCrew
import com.beti1205.movieapp.ui.movies.details.MovieDetailsPreviewDataProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieDetails(
    movieDetails: MovieDetails?,
    cast: List<Cast>?,
    crew: List<Crew>?,
    onPersonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        SectionMovie(movieDetails = movieDetails)
        SectionCast(
            cast = cast,
            onPersonClicked = onPersonClicked
        )
        SectionCrew(
            crew = crew,
            onPersonClicked = onPersonClicked
        )
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
            MovieDetails(
                movieDetails = MovieDetailsPreviewDataProvider.movie,
                cast = CreditsPreviewDataProvider.credits.cast,
                crew = CreditsPreviewDataProvider.credits.crew,
                onPersonClicked = {}
            )
        }
    }
}

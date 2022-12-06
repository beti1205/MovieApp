package com.beti1205.movieapp.ui.movies.details.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetails
import com.beti1205.movieapp.ui.common.widget.Details
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.movies.details.MovieDetailsPreviewDataProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun SectionMovie(
    movieDetails: MovieDetails?,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        movieDetails?.let { movieDetails ->
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
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SectionMoviePreview() {
    MovieAppTheme {
        Surface {
            SectionMovie(
                movieDetails = MovieDetailsPreviewDataProvider.movie
            )
        }
    }
}

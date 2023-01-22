package com.beti1205.movieapp.ui.movies.details.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.beti1205.movieapp.feature.fetchcredits.data.Credits
import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetails
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.common.widget.details.Details
import com.beti1205.movieapp.ui.movies.details.widget.credits.MovieCredits
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieDetails(
    movieDetails: MovieDetails?,
    credits: Credits?,
    favorite: Boolean,
    isLoggedIn: Boolean,
    onFavoriteClicked: (Boolean) -> Unit,
    onPersonClicked: (Int) -> Unit,
    onButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        movieDetails?.let { movieDetails ->
            Details(
                id = movieDetails.id,
                posterPath = movieDetails.posterPath,
                title = movieDetails.title,
                votes = movieDetails.votes,
                releaseDate = movieDetails.releaseDate,
                overview = movieDetails.overview,
                genres = movieDetails.genres,
                favorite = favorite,
                isLoggedIn = isLoggedIn,
                onButtonClicked = onButtonClicked,
                onFavoriteClicked = onFavoriteClicked
            )
            StandardDivider()
            if (credits != null) {
                MovieCredits(credits, onPersonClicked)
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
fun MovieDetailsPreview(
    @PreviewParameter(MovieDetailsPreviewProvider::class)
    state: Pair<MovieDetails, Credits>
) {
    MovieAppTheme {
        Surface {
            MovieDetails(
                movieDetails = state.first,
                credits = state.second,
                favorite = false,
                isLoggedIn = false,
                onFavoriteClicked = {},
                onPersonClicked = {},
                onButtonClicked = {}
            )
        }
    }
}

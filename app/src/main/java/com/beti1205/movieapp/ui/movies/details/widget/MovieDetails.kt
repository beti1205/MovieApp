/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.details.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.beti1205.movieapp.feature.credits.data.Credits
import com.beti1205.movieapp.feature.moviedetails.data.MovieDetails
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.common.widget.details.Details
import com.beti1205.movieapp.ui.movies.details.widget.credits.MovieCredits
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieDetails(
    movieDetails: MovieDetails?,
    credits: Credits?,
    favorite: Boolean,
    watchlist: Boolean,
    isLoggedIn: Boolean,
    onFavoriteClicked: (Boolean) -> Unit,
    onWatchlistIconClicked: (Boolean) -> Unit,
    onPersonClicked: (Int) -> Unit,
    onReviewsClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        movieDetails?.let { movieDetails ->
            Details(
                id = movieDetails.id,
                posterPath = movieDetails.posterPath,
                title = movieDetails.title,
                votes = movieDetails.votes,
                releaseDate = movieDetails.releaseDate,
                overview = movieDetails.overview,
                genres = movieDetails.genres,
                isFavorite = favorite,
                isAddedToWatchlist = watchlist,
                isLoggedIn = isLoggedIn,
                onReviewsClicked = onReviewsClicked,
                onFavoriteClicked = onFavoriteClicked,
                onWatchlistIconClicked = onWatchlistIconClicked
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
                watchlist = false,
                isLoggedIn = false,
                onFavoriteClicked = {},
                onPersonClicked = {},
                onReviewsClicked = {},
                onWatchlistIconClicked = {}
            )
        }
    }
}

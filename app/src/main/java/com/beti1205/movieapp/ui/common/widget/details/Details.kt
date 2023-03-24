/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common.widget.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.BookmarkAdded
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.outlined.BookmarkAdd
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.common.data.Genre
import com.beti1205.movieapp.feature.moviedetails.data.MovieDetails
import com.beti1205.movieapp.ui.common.widget.Overview
import com.beti1205.movieapp.ui.common.widget.Rating
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun Details(
    id: Int,
    posterPath: String?,
    title: String,
    votes: String,
    releaseDate: String?,
    overview: String,
    genres: List<Genre>?,
    isFavorite: Boolean,
    isAddedToWatchlist: Boolean,
    isLoggedIn: Boolean,
    modifier: Modifier = Modifier,
    onFavoriteClicked: (Boolean) -> Unit,
    onReviewsClicked: (Int) -> Unit,
    onWatchlistIconClicked: (Boolean) -> Unit
) {
    Column(
        modifier = modifier
    ) {
        DetailsPoster(posterPath = posterPath)
        Column(
            modifier = Modifier.padding(
                start = 16.dp,
                end = 16.dp,
                top = 8.dp
            )
        ) {
            Genres(genres = genres)
            Row {
                DetailsTitle(
                    title = title,
                    modifier = Modifier.weight(1f)
                )
                Rating(
                    votes = votes,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            ReleaseDate(releaseDate = releaseDate)
            if (overview.isNotEmpty()) {
                Overview(overview)
            }
            Row(modifier = Modifier.padding(top = 8.dp)) {
                ReviewsButton(onReviewsClicked = onReviewsClicked, id = id)
                if (isLoggedIn) {
                    FavoriteButton(
                        isFavorite = isFavorite,
                        onFavoriteClicked = onFavoriteClicked
                    )
                    AddToWatchlistButton(
                        isAddedToWatchlist = isAddedToWatchlist,
                        onWatchlistIconClicked = onWatchlistIconClicked
                    )
                }
            }
        }
    }
}

@Composable
private fun FavoriteButton(
    isFavorite: Boolean,
    onFavoriteClicked: (Boolean) -> Unit
) {
    IconButton(onClick = {
        onFavoriteClicked(!isFavorite)
    }) {
        if (isFavorite) {
            Icon(
                imageVector = Icons.Filled.Favorite,
                contentDescription = null,
                tint = MaterialTheme.colors.secondary
            )
        } else {
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
                contentDescription = null
            )
        }
    }
}

@Composable
private fun AddToWatchlistButton(
    isAddedToWatchlist: Boolean,
    onWatchlistIconClicked: (Boolean) -> Unit
) {
    IconButton(onClick = {
        onWatchlistIconClicked(!isAddedToWatchlist)
    }) {
        if (isAddedToWatchlist) {
            Icon(
                imageVector = Icons.Filled.BookmarkAdded,
                contentDescription = null,
                tint = MaterialTheme.colors.secondary
            )
        } else {
            Icon(
                imageVector = Icons.Outlined.BookmarkAdd,
                contentDescription = null
            )
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun DetailsPreview(@PreviewParameter(DetailsPreviewProvider::class) movieDetails: MovieDetails) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            movieDetails.apply {
                Details(
                    id = id,
                    posterPath = posterPath,
                    title = title,
                    votes = votes,
                    releaseDate = releaseDate,
                    overview = overview,
                    genres = genres,
                    isFavorite = false,
                    isAddedToWatchlist = false,
                    isLoggedIn = false,
                    onFavoriteClicked = {},
                    onReviewsClicked = {},
                    onWatchlistIconClicked = {}
                )
            }
        }
    }
}

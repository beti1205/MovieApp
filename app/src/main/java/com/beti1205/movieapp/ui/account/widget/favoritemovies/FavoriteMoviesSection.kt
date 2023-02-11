/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account.widget.favoritemovies

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.feature.movies.domain.FavoriteListOrder
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.theme.SonicSilver

@Composable
fun FavoriteMoviesSection(
    movies: List<Movie>,
    order: FavoriteListOrder,
    onOrderChanged: (FavoriteListOrder) -> Unit,
    onMovieClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultCard {
        Column(modifier = modifier.fillMaxWidth()) {
            Row {
                AccountSectionHeader(
                    text = stringResource(R.string.favorite_movies_section_header),
                    modifier = Modifier.padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                FavoriteMoviesOrder(order = order, onOrderChanged = onOrderChanged)
            }
            AnimatedVisibility(visible = movies.isEmpty()) {
                FavoriteListEmptyState(
                    text = stringResource(R.string.favorite_movies_empty_state_message)
                )
            }
            AnimatedVisibility(
                visible = movies.isNotEmpty()
            ) {
                FavoriteMovieList(
                    movies = movies,
                    onMovieClicked = onMovieClicked
                )
            }
        }
    }
}

@Composable
private fun FavoriteMoviesOrder(
    order: FavoriteListOrder,
    onOrderChanged: (FavoriteListOrder) -> Unit
) {
    IconButton(
        onClick = {
            FavoriteListOrder.availableValues()
                .first { it != order }
                .let { onOrderChanged(it) }
        }
    ) {
        if (order == FavoriteListOrder.LATEST) {
            Icon(
                painter = painterResource(id = R.drawable.sort_descending),
                contentDescription = null,
                tint = SonicSilver
            )
        } else {
            Icon(
                painter = painterResource(id = R.drawable.sort_ascending),
                contentDescription = null,
                tint = SonicSilver
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
fun FavoriteMoviesSectionPreview(
    @PreviewParameter(
        FavoriteMoviesSectionPreviewProvider::class
    ) movies: List<Movie>
) {
    MovieAppTheme {
        Surface(Modifier.fillMaxSize()) {
            FavoriteMoviesSection(
                movies = movies,
                order = FavoriteListOrder.LATEST,
                onOrderChanged = {},
                onMovieClicked = {}
            )
        }
    }
}

class FavoriteMoviesSectionPreviewProvider : PreviewParameterProvider<List<Movie>> {
    override val values = sequenceOf(
        listOf(
            Movie(
                id = 985939,
                title = "The Godfather II",
                overview = "In the continuing saga of the Corleone crime family, a young " +
                    "Vito Corleone grows up in Sicily and in 1910s New York.",
                popularity = 63.191,
                adult = false,
                voteCount = 10364,
                voteAverage = 8.602,
                language = "en",
                posterPath = "/spCAxD99U1A6jsiePFoqdEcY0dG.jpg",
                originalTitle = "The Godfather Part II",
                releaseDate = "1974-12-20"
            ),
            Movie(
                id = 497,
                title = "The Green Mile",
                overview = "A supernatural tale set on death row in a Southern prison," +
                    " where gentle giant John Coffey possesses the mysterious power to " +
                    "heal people's ailments.When the cell block's head guard, Paul Edgecomb," +
                    " recognizes Coffey's miraculous gift, he tries desperately to help stave off " +
                    "the condemned man's execution.",
                popularity = 95.84,
                adult = false,
                voteCount = 14744,
                voteAverage = 8.509,
                language = "en",
                posterPath = null,
                originalTitle = "The Green Mile",
                releaseDate = null
            )
        ),
        emptyList()
    )
}

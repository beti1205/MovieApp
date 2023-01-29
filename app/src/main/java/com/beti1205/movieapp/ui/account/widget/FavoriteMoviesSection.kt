package com.beti1205.movieapp.ui.account.widget

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.movies.data.Movie

@Composable
fun FavoriteMoviesSection(
    movies: List<Movie>,
    onMovieClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultCard {
        Column(modifier = modifier) {
            AccountSectionHeader(
                text = "Favorite movies",
                modifier = Modifier.padding(top = 8.dp)
            )
            AnimatedVisibility(
                visible = movies.isNotEmpty()
            ) {
                FavoriteMovieList(movies = movies, onMovieClicked = onMovieClicked)
            }
        }
    }
}

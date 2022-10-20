package com.beti1205.movieapp.ui.movies.details.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchmovies.data.Movie

@Composable
fun MovieDetails(
    movie: Movie?,
    modifier: Modifier = Modifier
) {
    if (movie != null) {
        Column(modifier = modifier) {
            Poster(posterPath = movie.posterPath)
            Column(modifier = Modifier.padding(16.dp)) {
                Row {
                    MovieTitle(title = movie.title)
                    Spacer(modifier = Modifier.weight(1f))
                    Rating(movie.votes)
                }
                Text(
                    text = movie.releaseDate,
                    style = MaterialTheme.typography.caption
                )
                Text(
                    text = movie.overview,
                    style = MaterialTheme.typography.body2,
                    modifier = Modifier.padding(top = 8.dp),
                    textAlign = TextAlign.Justify
                )
            }
        }
    }
}

package com.beti1205.movieapp.ui.movies.common.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchmovies.data.Movie
import com.beti1205.movieapp.ui.movies.common.MoviePreviewDataProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun MovieItem(
    movie: Movie,
    onMovieClicked: (Movie) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onMovieClicked(movie) },
        shape = RoundedCornerShape(16.dp),
        contentColor = MaterialTheme.colors.onPrimary,
        backgroundColor = MaterialTheme.colors.primaryVariant,
        modifier = modifier.padding(8.dp),
        elevation = 16.dp
    ) {
        Column {
            MovieItemPoster(posterPath = movie.posterPath)
            Text(
                text = movie.title,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .width(200.dp)
                    .padding(8.dp),
                textAlign = TextAlign.Center
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
fun MovieItemPreview() {
    MovieAppTheme {
        MovieItem(movie = MoviePreviewDataProvider.movie, onMovieClicked = {})
    }
}

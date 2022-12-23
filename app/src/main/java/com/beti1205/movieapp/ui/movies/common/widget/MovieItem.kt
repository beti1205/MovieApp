package com.beti1205.movieapp.ui.movies.common.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchmovies.data.Movie
import com.beti1205.movieapp.ui.common.widget.list.ListItem
import com.beti1205.movieapp.ui.common.widget.list.ListItemPreviewProvider
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
        ListItem(movie.posterPath, movie.title, movie.votes)
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun MovieItemPreview(@PreviewParameter(ListItemPreviewProvider::class) movie: Movie) {
    MovieAppTheme {
        MovieItem(movie = movie, onMovieClicked = {})
    }
}

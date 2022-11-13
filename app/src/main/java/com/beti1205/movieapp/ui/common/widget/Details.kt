package com.beti1205.movieapp.ui.common.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.ui.movies.common.MoviePreviewDataProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun Details(
    posterPath: String?,
    title: String,
    votes: String,
    releaseDate: String?,
    overview: String,
    modifier: Modifier = Modifier,
    genres: @Composable () -> Unit = {}
) {
    Column(modifier = modifier) {
        Poster(posterPath = posterPath)
        genres()
        Column(modifier = Modifier.padding(16.dp)) {
            Row {
                Title(title = title)
                Spacer(modifier = Modifier.weight(1f))
                Rating(votes = votes)
            }
            ReleaseDate(releaseDate = releaseDate)
            Overview(overview)
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun DetailsPreview() {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            with(MoviePreviewDataProvider) {
                Details(
                    movie.posterPath,
                    movie.title,
                    movie.votes,
                    movie.releaseDate,
                    movie.overview
                )
            }
        }
    }
}

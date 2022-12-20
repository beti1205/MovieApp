package com.beti1205.movieapp.ui.common.widget.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.ui.common.widget.Overview
import com.beti1205.movieapp.ui.common.widget.Rating
import com.beti1205.movieapp.ui.movies.details.MovieDetailsPreviewDataProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun Details(
    posterPath: String?,
    title: String,
    votes: String,
    releaseDate: String?,
    overview: String,
    modifier: Modifier = Modifier,
    genres: List<Genre>?
) {
    Column(
        modifier = modifier
    ) {
        DetailsPoster(posterPath = posterPath)
        Column(
            modifier = Modifier.padding(
                horizontal = 16.dp,
                vertical = 8.dp
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
            with(MovieDetailsPreviewDataProvider) {
                Details(
                    posterPath = movie.posterPath,
                    title = movie.title,
                    votes = movie.votes,
                    releaseDate = movie.releaseDate,
                    overview = movie.overview,
                    genres = genres
                )
            }
        }
    }
}

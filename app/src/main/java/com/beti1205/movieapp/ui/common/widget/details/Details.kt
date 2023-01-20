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
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetails
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
    modifier: Modifier = Modifier,
    onFavoriteClicked: (Boolean) -> Unit,
    onButtonClicked: (Int) -> Unit = {}
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
                ReviewsButton(onButtonClicked = onButtonClicked, id = id)
                FavoriteButton(
                    onFavoriteClicked = onFavoriteClicked
                )
            }
        }
    }
}

@Composable
private fun FavoriteButton(
    onFavoriteClicked: (Boolean) -> Unit
) {
    var favorite by remember {
        mutableStateOf(false)
    }

    IconButton(onClick = {
        favorite = !favorite
        onFavoriteClicked(favorite)
    }) {
        if (favorite) {
            Icon(
                imageVector = Icons.Filled.FavoriteBorder,
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
                    onFavoriteClicked = {},
                    onButtonClicked = {}
                )
            }
        }
    }
}

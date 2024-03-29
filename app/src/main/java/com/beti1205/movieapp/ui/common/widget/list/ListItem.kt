/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common.widget.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.ui.common.widget.Rating
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun ListItem(
    posterPath: String?,
    title: String,
    votes: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ItemPoster(posterPath = posterPath)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            ItemTitle(
                title = title,
                modifier = Modifier.weight(1f).alignByBaseline()
            )
            Rating(
                votes = votes,
                modifier = Modifier.padding(top = 8.dp, end = 8.dp).alignByBaseline()
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
fun ListItemPreview(@PreviewParameter(ListItemPreviewProvider::class) movie: Movie) {
    MovieAppTheme {
        Surface {
            ListItem(
                posterPath = movie.posterPath,
                title = movie.title,
                votes = movie.votes
            )
        }
    }
}

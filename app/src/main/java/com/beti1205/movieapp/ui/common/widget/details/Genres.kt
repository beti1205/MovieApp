/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common.widget.details

import android.content.res.Configuration
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.common.data.Genre
import com.beti1205.movieapp.feature.moviedetails.data.MovieDetails
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Genres(
    genres: List<Genre>?,
    modifier: Modifier = Modifier
) {
    FlowRow(
        mainAxisSpacing = 8.dp,
        modifier = modifier
    ) {
        genres?.forEach { genre ->
            Chip(
                onClick = { },
                enabled = false,
                colors = ChipDefaults.chipColors(
                    disabledBackgroundColor = MaterialTheme.colors.primaryVariant,
                    disabledContentColor = MaterialTheme.colors.onPrimary
                )
            ) {
                Text(
                    text = genre.name
                )
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
fun GenresPreview(@PreviewParameter(DetailsPreviewProvider::class) movieDetails: MovieDetails) {
    MovieAppTheme {
        Surface {
            Genres(genres = movieDetails.genres)
        }
    }
}

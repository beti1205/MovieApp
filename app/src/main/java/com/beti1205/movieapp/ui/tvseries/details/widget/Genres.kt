package com.beti1205.movieapp.ui.tvseries.details.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Chip
import androidx.compose.material.ChipDefaults
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Genre
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.tvseries.common.TVSeriesPreviewDataProvider
import com.google.accompanist.flowlayout.FlowRow

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun Genres(
    genres: List<Genre>?,
    modifier: Modifier = Modifier
) {
    FlowRow(modifier = modifier.padding(start = 8.dp, end = 8.dp, top = 8.dp)) {
        genres?.forEach { genre ->
            Chip(
                onClick = { },
                enabled = false,
                colors = ChipDefaults.chipColors(
                    disabledBackgroundColor = MaterialTheme.colors.primaryVariant,
                    disabledContentColor = MaterialTheme.colors.onPrimary
                ),
                modifier = Modifier.padding(horizontal = 8.dp)
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
fun GenresPreview() {
    MovieAppTheme {
        Surface {
            Genres(genres = TVSeriesPreviewDataProvider.genresList)
        }
    }
}

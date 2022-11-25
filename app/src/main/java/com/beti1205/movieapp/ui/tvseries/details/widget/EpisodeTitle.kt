package com.beti1205.movieapp.ui.tvseries.details.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.tvseries.common.TVSeriesPreviewDataProvider

@Composable
fun EpisodeTitle(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.secondary,
        modifier = modifier.padding(top = 8.dp)
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun EpisodeTitlePreview() {
    MovieAppTheme {
        Surface {
            EpisodeTitle(
                name = TVSeriesPreviewDataProvider.episodesList.first().name
            )
        }
    }
}

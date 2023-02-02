/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.details.widget.episodes

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.tvepisodes.data.Episode
import com.beti1205.movieapp.ui.theme.MovieAppTheme

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
fun EpisodeTitlePreview(
    @PreviewParameter(EpisodeItemPreviewProvider::class)
    episode: Episode
) {
    MovieAppTheme {
        Surface {
            EpisodeTitle(
                name = episode.name
            )
        }
    }
}

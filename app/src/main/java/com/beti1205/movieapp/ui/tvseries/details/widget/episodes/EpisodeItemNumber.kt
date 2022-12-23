package com.beti1205.movieapp.ui.tvseries.details.widget.episodes

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun EpisodeItemNumber(numberOfEpisode: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(
            id = R.string.episode_number,
            numberOfEpisode
        ),
        style = MaterialTheme.typography.body1,
        modifier = modifier.padding(bottom = 8.dp)
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun EpisodeItemNumberPreview(
    @PreviewParameter(EpisodeItemPreviewProvider::class)
    episode: Episode
) {
    MovieAppTheme {
        Surface {
            EpisodeItemNumber(
                numberOfEpisode = episode.numberOfEpisode
            )
        }
    }
}

package com.beti1205.movieapp.ui.tvseries.details.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.ui.common.widget.Overview
import com.beti1205.movieapp.ui.common.widget.ReleaseDate
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.tvseries.common.TVSeriesPreviewDataProvider

@Composable
fun EpisodeItem(
    item: Episode,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        Text(
            text = stringResource(
                id = R.string.episode_number,
                item.numberOfEpisode
            ),
            style = MaterialTheme.typography.body1,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(bottom = 8.dp)
        )
        PosterEpisode(posterPath = item.posterPath)
        Text(
            text = item.name,
            style = MaterialTheme.typography.subtitle1,
            color = MaterialTheme.colors.secondary,
            modifier = Modifier.align(Alignment.CenterHorizontally).padding(top = 8.dp)
        )
        ReleaseDate(
            releaseDate = item.episodeAirDate,
            modifier = Modifier.align(Alignment.End)
        )
        Overview(overview = item.overview)
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun EpisodeItemPreview() {
    MovieAppTheme {
        Surface {
            EpisodeItem(item = TVSeriesPreviewDataProvider.episodesList.first())
        }
    }
}

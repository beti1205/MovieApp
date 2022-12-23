package com.beti1205.movieapp.ui.tvseries.details.widget.episodes

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.ui.common.widget.Overview
import com.beti1205.movieapp.ui.common.widget.details.ReleaseDate
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun EpisodeItem(
    item: Episode,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        EpisodeItemNumber(
            numberOfEpisode = item.numberOfEpisode,
            modifier = Modifier.align(Alignment.CenterHorizontally)
        )
        EpisodePoster(posterPath = item.posterPath)
        EpisodeTitle(name = item.name, modifier = Modifier.align(Alignment.CenterHorizontally))
        ReleaseDate(
            releaseDate = item.episodeAirDate,
            modifier = Modifier
                .align(Alignment.End)
                .padding(top = 8.dp)
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
fun EpisodeItemPreview(
    @PreviewParameter(EpisodeItemPreviewProvider::class)
    episode: Episode
) {
    MovieAppTheme {
        Surface {
            EpisodeItem(item = episode)
        }
    }
}

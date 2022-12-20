package com.beti1205.movieapp.ui.tvseries.details.widget.episodes

import android.content.res.Configuration
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.ui.common.widget.listItemHorizontalPadding
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.tvseries.common.TVSeriesPreviewDataProvider

@Composable
fun EpisodeList(
    episodes: List<Episode>?,
    modifier: Modifier = Modifier
) {
    if (episodes != null) {
        LazyRow(
            modifier = modifier
                .padding(vertical = 8.dp, horizontal = 16.dp)
                .animateContentSize()
        ) {
            itemsIndexed(
                items = episodes,
                key = { _, item -> item.id }
            ) { index, item ->
                Column(
                    modifier = Modifier
                        .listItemHorizontalPadding(episodes, index)
                        .width(300.dp)
                ) {
                    EpisodeItem(item)
                }
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
fun EpisodeListPreview() {
    MovieAppTheme {
        Surface {
            EpisodeList(episodes = TVSeriesPreviewDataProvider.episodesList)
        }
    }
}

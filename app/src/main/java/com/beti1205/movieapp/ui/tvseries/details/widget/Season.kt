package com.beti1205.movieapp.ui.tvseries.details.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Season
import com.beti1205.movieapp.ui.common.widget.Overview
import com.beti1205.movieapp.ui.common.widget.ReleaseDate
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.tvseries.common.TVSeriesPreviewDataProvider

@Composable
fun Season(
    selectedSeason: Season?,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        if (selectedSeason != null) {
            PosterSeason(posterPath = selectedSeason.posterPath)
            Column(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .align(Alignment.CenterVertically)
            ) {
                ReleaseDate(releaseDate = selectedSeason.airDate)
                Text(
                    text = stringResource(
                        id = R.string.episodes_count,
                        selectedSeason.episodes
                    ),
                    style = MaterialTheme.typography.body2
                )
                Overview(overview = selectedSeason.overview)
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
fun SeasonPreview() {
    MovieAppTheme {
        Surface {
            Season(selectedSeason = TVSeriesPreviewDataProvider.seasonsList.first())
        }
    }
}

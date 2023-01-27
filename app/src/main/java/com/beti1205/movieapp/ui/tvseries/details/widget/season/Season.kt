package com.beti1205.movieapp.ui.tvseries.details.widget.season

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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.tvseriesdetails.data.Season
import com.beti1205.movieapp.ui.common.widget.Overview
import com.beti1205.movieapp.ui.common.widget.details.ReleaseDate
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun Season(
    selectedSeason: Season,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)) {
        SeasonPoster(posterPath = selectedSeason.posterPath)
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

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SeasonPreview(
    @PreviewParameter(SeasonPreviewProvider::class)
    season: Season
) {
    MovieAppTheme {
        Surface {
            Season(selectedSeason = season)
        }
    }
}

class SeasonPreviewProvider : PreviewParameterProvider<Season> {
    override val values = sequenceOf(
        Season(
            airDate = "2021-09-21",
            episodeCount = 10,
            id = 170644,
            name = "Limited Series",
            overview = "In prison, Jeff's newfound fame makes him a target.",
            posterPath = "/h7YlJ1Mhg6jCZiHToUiKqHdzMO9.jpg",
            seasonNumber = 1
        ),
        Season(
            airDate = "2008-01-20",
            episodeCount = 7,
            id = 3572,
            name = "Season 1",
            overview = "When an unassuming high school chemistry teacher discovers he has " +
                "a rare form of lung cancer, he decides to team up with a former student " +
                "and create a top of the line crystal meth in a used RV, to provide for his " +
                "family once he is gone.",
            posterPath = "/1BP4xYv9ZG4ZVHkL7ocOziBbSYH.jpg",
            seasonNumber = 1
        )
    )
}

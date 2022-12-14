package com.beti1205.movieapp.ui.tvseries.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Season
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.TVSeriesDetails
import com.beti1205.movieapp.ui.common.widget.Details
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.movies.details.widget.EmptyStateMessage
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.tvseries.common.TVSeriesPreviewDataProvider
import com.beti1205.movieapp.ui.tvseries.details.widget.EpisodeList
import com.beti1205.movieapp.ui.tvseries.details.widget.Season
import com.beti1205.movieapp.ui.tvseries.details.widget.SeasonDropdown

@Composable
fun TVSeriesDetailsScreen(viewModel: TVSeriesDetailsViewModel) {
    val tvSeriesDetails by viewModel.tvSeriesDetails.collectAsState()
    val selectedSeason by viewModel.selectedSeason.collectAsState()
    val episodes by viewModel.episodes.collectAsState()
    val hasError by viewModel.hasError.collectAsState()

    TVSeriesDetailsScreen(
        tvSeriesDetails = tvSeriesDetails,
        selectedSeason = selectedSeason,
        onSeasonSelected = viewModel::setSelectedSeason,
        episodes = episodes,
        hasError = hasError
    )
}

@Composable
fun TVSeriesDetailsScreen(
    tvSeriesDetails: TVSeriesDetails?,
    selectedSeason: Season?,
    onSeasonSelected: (Season) -> Unit,
    episodes: List<Episode>?,
    hasError: Boolean
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            when {
                hasError -> EmptyStateMessage()
                else -> Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                    tvSeriesDetails?.apply {
                        Details(
                            posterPath = posterPath,
                            title = name,
                            votes = votes,
                            releaseDate = firstAirDate,
                            overview = overview,
                            genres = genres
                        )
                        StandardDivider()
                        SeasonDropdown(
                            selectedSeason = selectedSeason,
                            seasons = seasons,
                            onSeasonSelected = onSeasonSelected
                        )
                        Season(selectedSeason)
                        StandardDivider()
                        EpisodeList(episodes)
                    }
                }
            }
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    heightDp = 2000
)
@Composable
fun TVSeriesDetailsScreenPreview() {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TVSeriesDetailsScreen(
                tvSeriesDetails = TVSeriesPreviewDataProvider.tvSeriesDetails,
                selectedSeason = TVSeriesPreviewDataProvider.seasonsList.first(),
                onSeasonSelected = {},
                episodes = TVSeriesPreviewDataProvider.episodesList,
                hasError = false
            )
        }
    }
}

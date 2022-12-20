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
import com.beti1205.movieapp.feature.fetchcredits.data.Credits
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Season
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.TVSeriesDetails
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.common.widget.credits.SectionCast
import com.beti1205.movieapp.ui.common.widget.credits.SectionCrew
import com.beti1205.movieapp.ui.common.widget.details.Details
import com.beti1205.movieapp.ui.movies.details.widget.EmptyStateMessage
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.tvseries.common.TVSeriesPreviewDataProvider
import com.beti1205.movieapp.ui.tvseries.details.widget.episodes.EpisodeList
import com.beti1205.movieapp.ui.tvseries.details.widget.season.Season
import com.beti1205.movieapp.ui.tvseries.details.widget.season.SeasonDropdown

@Composable
fun TVSeriesDetailsScreen(
    viewModel: TVSeriesDetailsViewModel,
    onPersonClicked: (Int) -> Unit
) {
    val tvSeriesDetails by viewModel.tvSeriesDetails.collectAsState()
    val selectedSeason by viewModel.selectedSeason.collectAsState()
    val episodes by viewModel.episodes.collectAsState()
    val hasError by viewModel.hasError.collectAsState()
    val credits by viewModel.credits.collectAsState()

    TVSeriesDetailsScreen(
        tvSeriesDetails = tvSeriesDetails,
        selectedSeason = selectedSeason,
        onSeasonSelected = viewModel::setSelectedSeason,
        episodes = episodes,
        hasError = hasError,
        credits = credits,
        onPersonClicked = onPersonClicked
    )
}

@Composable
fun TVSeriesDetailsScreen(
    tvSeriesDetails: TVSeriesDetails?,
    selectedSeason: Season?,
    onSeasonSelected: (Season) -> Unit,
    episodes: List<Episode>?,
    hasError: Boolean,
    credits: Credits?,
    onPersonClicked: (Int) -> Unit
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
                        if (credits != null) {
                            SectionCast(cast = credits.cast, onPersonClicked = onPersonClicked)
                            SectionCrew(crew = credits.crew, onPersonClicked = onPersonClicked)
                            StandardDivider()
                        }
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
                hasError = false,
                credits = null,
                onPersonClicked = {}
            )
        }
    }
}

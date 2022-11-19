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
import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Season
import com.beti1205.movieapp.ui.common.widget.Details
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.tvseries.common.TVSeriesPreviewDataProvider
import com.beti1205.movieapp.ui.tvseries.details.widget.EpisodeList
import com.beti1205.movieapp.ui.tvseries.details.widget.Season
import com.beti1205.movieapp.ui.tvseries.details.widget.SeasonDropdown

@Composable
fun TVSeriesDetailsScreen(viewModel: TVSeriesDetailsViewModel) {
    val tvSeries by viewModel.selectedTVSeries.collectAsState()
    val genres by viewModel.genres.collectAsState()
    val selectedSeason by viewModel.selectedSeason.collectAsState()
    val seasons by viewModel.seasons.collectAsState()
    val episodes by viewModel.episodes.collectAsState()

    TVSeriesDetailsScreen(
        tvSeries = tvSeries,
        genres = genres,
        selectedSeason = selectedSeason,
        seasons = seasons,
        onOptionSelected = viewModel::setSelectedSeasonPosition,
        episodes = episodes
    )
}

@Composable
fun TVSeriesDetailsScreen(
    tvSeries: TVSeries?,
    genres: List<Genre>?,
    selectedSeason: Season?,
    seasons: List<Season>?,
    onOptionSelected: (Int) -> Unit,
    episodes: List<Episode>?
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
                if (tvSeries != null) {
                    Details(
                        posterPath = tvSeries.posterPath,
                        title = tvSeries.name,
                        votes = tvSeries.votes,
                        releaseDate = tvSeries.firstAirDate,
                        overview = tvSeries.overview,
                        genres = genres
                    )
                }
                StandardDivider()
                if (seasons != null) {
                    SeasonDropdown(
                        value = selectedSeason?.name,
                        suggestions = seasons,
                        onOptionSelected = onOptionSelected

                    )
                }
                Season(selectedSeason)
                StandardDivider()
                EpisodeList(episodes)
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
fun TVSeriesDetailsScreenPreview() {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TVSeriesDetailsScreen(
                tvSeries = TVSeriesPreviewDataProvider.tvSeries,
                genres = TVSeriesPreviewDataProvider.genres,
                selectedSeason = TVSeriesPreviewDataProvider.seasonsList.first(),
                seasons = TVSeriesPreviewDataProvider.seasonsList,
                onOptionSelected = {},
                episodes = TVSeriesPreviewDataProvider.episodesList
            )
        }
    }
}

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
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.feature.fetchcredits.data.Credits
import com.beti1205.movieapp.feature.fetchcredits.data.Crew
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Season
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.TVSeriesDetails
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.common.widget.credits.Credits
import com.beti1205.movieapp.ui.common.widget.details.Details
import com.beti1205.movieapp.ui.movies.details.widget.EmptyStateMessage
import com.beti1205.movieapp.ui.theme.MovieAppTheme
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
                            id = id,
                            posterPath = posterPath,
                            title = name,
                            votes = votes,
                            releaseDate = firstAirDate,
                            overview = overview,
                            genres = genres
                        )
                        StandardDivider()
                        if (credits != null) {
                            Credits(credits, onPersonClicked)
                        }
                        if (selectedSeason != null) {
                            SeasonDropdown(
                                selectedSeason = selectedSeason,
                                seasons = seasons,
                                onSeasonSelected = onSeasonSelected
                            )
                            Season(selectedSeason)
                        }
                        if (!episodes.isNullOrEmpty()) {
                            StandardDivider()
                            EpisodeList(episodes)
                        }
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
fun TVSeriesDetailsScreenPreview(
    @PreviewParameter(TVSeriesDetailsScreenPreviewProvider::class)
    tvSeriesDetailsScreen: TVSeriesDetailsScreen
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            TVSeriesDetailsScreen(
                tvSeriesDetails = tvSeriesDetailsScreen.tvSeriesDetails,
                selectedSeason = tvSeriesDetailsScreen.selectedSeason,
                onSeasonSelected = {},
                episodes = tvSeriesDetailsScreen.episodes,
                hasError = tvSeriesDetailsScreen.hasError,
                credits = tvSeriesDetailsScreen.credits,
                onPersonClicked = {}
            )
        }
    }
}

data class TVSeriesDetailsScreen(
    val tvSeriesDetails: TVSeriesDetails?,
    val selectedSeason: Season?,
    val episodes: List<Episode>?,
    val hasError: Boolean,
    val credits: Credits?
)

class TVSeriesDetailsScreenPreviewProvider : PreviewParameterProvider<TVSeriesDetailsScreen> {
    override val values = sequenceOf(
        TVSeriesDetailsScreen(
            tvSeriesDetails = TVSeriesDetails(
                id = 80752,
                overview = "A virus has decimated humankind. Those who survived emerged blind",
                name = "See",
                firstAirDate = "2019-11-01",
                voteAverage = 8.3,
                posterPath = "/lKDIhc9FQibDiBQ57n3ELfZCyZg.jpg",
                genres = listOf(
                    Genre(
                        id = 18,
                        name = "Drama"
                    ),
                    Genre(
                        id = 20,
                        name = "Crime"
                    )
                ),
                inProduction = true,
                lastAirDate = "2019-11-01",
                numberOfEpisodes = 2,
                numberOfSeasons = 2,
                seasons = listOf(
                    Season(
                        airDate = "2021-09-21",
                        episodeCount = 10,
                        id = 170644,
                        name = "Limited Series",
                        overview = "In prison, Jeff's newfound fame makes him a target.",
                        posterPath = "/h7YlJ1Mhg6jCZiHToUiKqHdzMO9.jpg",
                        seasonNumber = 1
                    )
                )
            ),
            selectedSeason = Season(
                airDate = "2021-09-21",
                episodeCount = 10,
                id = 170644,
                name = "Limited Series",
                overview = "In prison, Jeff's newfound fame makes him a target.",
                posterPath = "/h7YlJ1Mhg6jCZiHToUiKqHdzMO9.jpg",
                seasonNumber = 1
            ),
            episodes = listOf(
                Episode(
                    id = 1019694,
                    name = "Mijo",
                    overview = "As his troubles escalate to a boiling point, Jimmy finds himself in dire straits.",
                    posterPath = "/8XFhyx4xFCY2nZPThgOUF42G4fI.jpg",
                    episodeAirDate = "2015-02-09",
                    episodeNumber = 1
                )
            ),
            hasError = false,
            credits = Credits(
                id = 1,
                cast = listOf(
                    Cast(
                        id = 1,
                        name = "Grace Caroline Currey",
                        popularity = 8.9,
                        character = "Becky",
                        path = "/6chZcnjWEiFfpmB6D5BR9YUeIs9.jpg"
                    )
                ),
                crew = listOf(
                    Crew(
                        id = 90812,
                        name = "Scott Mann",
                        popularity = 7.356,
                        job = "Director",
                        department = "Directing",
                        path = "/8WygpUzfdfztZQqxGE5zn3rCedJ.jpg"
                    )
                )
            )
        ),
        TVSeriesDetailsScreen(
            tvSeriesDetails = null,
            selectedSeason = null,
            episodes = emptyList(),
            hasError = true,
            credits = null
        )
    )
}

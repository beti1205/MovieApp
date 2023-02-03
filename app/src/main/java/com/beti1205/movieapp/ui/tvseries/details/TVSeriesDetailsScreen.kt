/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.details

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.R
import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.feature.credits.data.Cast
import com.beti1205.movieapp.feature.credits.data.Credits
import com.beti1205.movieapp.feature.credits.data.Crew
import com.beti1205.movieapp.feature.tvepisodes.data.Episode
import com.beti1205.movieapp.feature.tvseriesdetails.data.Season
import com.beti1205.movieapp.feature.tvseriesdetails.data.TVSeriesDetails
import com.beti1205.movieapp.ui.common.widget.Error
import com.beti1205.movieapp.ui.common.widget.TopAppBar
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.tvseries.details.widget.TVSeriesDetailsScreenContent

@Composable
fun TVSeriesDetailsScreen(
    viewModel: TVSeriesDetailsViewModel,
    onPersonClicked: (Int) -> Unit,
    onReviewsClicked: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val tvSeriesDetails by viewModel.tvSeriesDetails.collectAsState()
    val selectedSeason by viewModel.selectedSeason.collectAsState()
    val episodes by viewModel.episodes.collectAsState()
    val hasError by viewModel.hasError.collectAsState()
    val credits by viewModel.credits.collectAsState()
    val favorite by viewModel.favorite.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val favoriteHasError by viewModel.favoriteHasError.collectAsState()

    TVSeriesDetailsScreen(
        tvSeriesDetails = tvSeriesDetails,
        credits = credits,
        selectedSeason = selectedSeason,
        episodes = episodes,
        favorite = favorite,
        isLoggedIn = isLoggedIn,
        hasError = hasError,
        favoriteHasError = favoriteHasError,
        onFavoriteErrorHandled = viewModel::onFavoriteErrorHandled,
        onSeasonSelected = viewModel::setSelectedSeason,
        onFavoriteClicked = viewModel::markFavorite,
        onPersonClicked = onPersonClicked,
        onReviewsClicked = onReviewsClicked,
        onBackPressed = onBackPressed
    )
}

@Composable
fun TVSeriesDetailsScreen(
    tvSeriesDetails: TVSeriesDetails?,
    credits: Credits?,
    selectedSeason: Season?,
    episodes: List<Episode>?,
    favorite: Boolean,
    isLoggedIn: Boolean,
    hasError: Boolean,
    favoriteHasError: Boolean,
    onFavoriteErrorHandled: () -> Unit,
    onSeasonSelected: (Season) -> Unit,
    onFavoriteClicked: (Boolean) -> Unit,
    onPersonClicked: (Int) -> Unit,
    onReviewsClicked: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val favoriteErrorMessage = stringResource(R.string.tv_series_details_favorite_error)

    TVSeriesDetailsScreenContent(
        scaffoldState = scaffoldState,
        tvSeriesDetails = tvSeriesDetails,
        credits = credits,
        selectedSeason = selectedSeason,
        episodes = episodes,
        favorite = favorite,
        hasError = hasError,
        isLoggedIn = isLoggedIn,
        onFavoriteClicked = onFavoriteClicked,
        onPersonClicked = onPersonClicked,
        onSeasonSelected = onSeasonSelected,
        onBackPressed = onBackPressed,
        onReviewsClicked = onReviewsClicked
    )

    if (favoriteHasError) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = favoriteErrorMessage
            )
            onFavoriteErrorHandled()
        }
    }
}

@Composable
private fun TVSeriesDetailsScreenContent(
    scaffoldState: ScaffoldState,
    tvSeriesDetails: TVSeriesDetails?,
    credits: Credits?,
    selectedSeason: Season?,
    episodes: List<Episode>?,
    favorite: Boolean,
    hasError: Boolean,
    isLoggedIn: Boolean,
    onFavoriteClicked: (Boolean) -> Unit,
    onPersonClicked: (Int) -> Unit,
    onSeasonSelected: (Season) -> Unit,
    onBackPressed: () -> Unit,
    onReviewsClicked: (Int) -> Unit
) {
    MovieAppTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = stringResource(id = R.string.tv_details_label),
                    onBackPressed = onBackPressed
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                when {
                    hasError -> Error()
                    else -> TVSeriesDetailsScreenContent(
                        tvSeriesDetails = tvSeriesDetails,
                        credits = credits,
                        selectedSeason = selectedSeason,
                        episodes = episodes,
                        favorite = favorite,
                        isLoggedIn = isLoggedIn,
                        onFavoriteClicked = onFavoriteClicked,
                        onPersonClicked = onPersonClicked,
                        onSeasonSelected = onSeasonSelected,
                        onReviewsClicked = onReviewsClicked
                    )
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
        TVSeriesDetailsScreen(
            tvSeriesDetails = tvSeriesDetailsScreen.tvSeriesDetails,
            credits = tvSeriesDetailsScreen.credits,
            selectedSeason = tvSeriesDetailsScreen.selectedSeason,
            episodes = tvSeriesDetailsScreen.episodes,
            favorite = tvSeriesDetailsScreen.favorite,
            isLoggedIn = tvSeriesDetailsScreen.isLoggedIn,
            hasError = tvSeriesDetailsScreen.hasError,
            favoriteHasError = tvSeriesDetailsScreen.favoriteHasError,
            onFavoriteErrorHandled = {},
            onSeasonSelected = {},
            onFavoriteClicked = {},
            onPersonClicked = {},
            onBackPressed = {},
            onReviewsClicked = {}
        )
    }
}

data class TVSeriesDetailsScreen(
    val tvSeriesDetails: TVSeriesDetails?,
    val selectedSeason: Season?,
    val episodes: List<Episode>?,
    val hasError: Boolean,
    val credits: Credits?,
    val favorite: Boolean,
    val isLoggedIn: Boolean,
    val favoriteHasError: Boolean
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
            ),
            favorite = true,
            isLoggedIn = true,
            favoriteHasError = false
        ),
        TVSeriesDetailsScreen(
            tvSeriesDetails = null,
            selectedSeason = null,
            episodes = emptyList(),
            hasError = true,
            credits = null,
            favorite = false,
            isLoggedIn = false,
            favoriteHasError = false
        )
    )
}

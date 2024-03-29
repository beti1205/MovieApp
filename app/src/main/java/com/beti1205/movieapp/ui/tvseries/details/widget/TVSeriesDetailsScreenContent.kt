/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.details.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.beti1205.movieapp.feature.credits.data.Credits
import com.beti1205.movieapp.feature.tvepisodes.data.Episode
import com.beti1205.movieapp.feature.tvseriesdetails.data.Season
import com.beti1205.movieapp.feature.tvseriesdetails.data.TVSeriesDetails
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.common.widget.details.Details
import com.beti1205.movieapp.ui.tvseries.details.widget.credits.TVSeriesCredits
import com.beti1205.movieapp.ui.tvseries.details.widget.episodes.EpisodeList
import com.beti1205.movieapp.ui.tvseries.details.widget.season.Season
import com.beti1205.movieapp.ui.tvseries.details.widget.season.SeasonDropdown

@Composable
fun TVSeriesDetailsScreenContent(
    tvSeriesDetails: TVSeriesDetails?,
    credits: Credits?,
    selectedSeason: Season?,
    episodes: List<Episode>?,
    isFavorite: Boolean,
    isAddedToWatchlist: Boolean,
    isLoggedIn: Boolean,
    onFavoriteClicked: (Boolean) -> Unit,
    onWatchlistIconClicked: (Boolean) -> Unit,
    onPersonClicked: (Int) -> Unit,
    onSeasonSelected: (Season) -> Unit,
    onReviewsClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.verticalScroll(rememberScrollState())) {
        tvSeriesDetails?.apply {
            Details(
                id = id,
                posterPath = posterPath,
                title = name,
                votes = votes,
                releaseDate = firstAirDate,
                overview = overview,
                genres = genres,
                isFavorite = isFavorite,
                isAddedToWatchlist = isAddedToWatchlist,
                isLoggedIn = isLoggedIn,
                onFavoriteClicked = onFavoriteClicked,
                onReviewsClicked = onReviewsClicked,
                onWatchlistIconClicked = onWatchlistIconClicked
            )
            StandardDivider()
            if (credits != null) {
                TVSeriesCredits(credits, onPersonClicked)
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

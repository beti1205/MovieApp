package com.beti1205.movieapp.ui.account.widget.favoritetvseries

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.ui.account.widget.favoritemovies.AccountSectionHeader
import com.beti1205.movieapp.ui.account.widget.favoritemovies.DefaultCard
import com.beti1205.movieapp.ui.account.widget.favoritemovies.FavoriteListEmptyState

@Composable
fun FavoriteTVSeriesSection(
    tvSeries: List<TVSeries>,
    onTVSeriesClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultCard {
        Column(modifier = modifier.fillMaxWidth()) {
            AccountSectionHeader(
                text = "Favorite tv series",
                modifier = Modifier.padding(top = 8.dp)
            )
            AnimatedVisibility(visible = tvSeries.isEmpty()) {
                FavoriteListEmptyState(
                    text = stringResource(R.string.favorite_tv_series_empty_state_message)
                )
            }
            AnimatedVisibility(
                visible = tvSeries.isNotEmpty()
            ) {
                FavoriteTVSeriesList(
                    tvSeries = tvSeries,
                    onTVSeriesClicked = onTVSeriesClicked
                )
            }
        }
    }
}

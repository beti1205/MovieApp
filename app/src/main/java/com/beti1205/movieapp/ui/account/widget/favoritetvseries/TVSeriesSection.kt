/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account.widget.favoritetvseries

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.common.data.ListOrder
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.ui.account.AccountScreenOrderType
import com.beti1205.movieapp.ui.account.widget.ListOrderButton
import com.beti1205.movieapp.ui.account.widget.favoritemovies.AccountSectionHeader
import com.beti1205.movieapp.ui.account.widget.favoritemovies.DefaultCard
import com.beti1205.movieapp.ui.account.widget.favoritemovies.FavoriteListEmptyState
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun TVSeriesSection(
    title: String,
    emptyStateMessage: String,
    tvSeries: List<TVSeries>,
    tvOrder: ListOrder,
    orderType: AccountScreenOrderType,
    onOrderChanged: (AccountScreenOrderType, ListOrder) -> Unit,
    onTVSeriesClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    DefaultCard {
        Column(modifier = modifier.fillMaxWidth()) {
            Row {
                AccountSectionHeader(
                    text = title,
                    modifier = Modifier.padding(top = 8.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                ListOrderButton(
                    order = tvOrder,
                    orderType = orderType,
                    onOrderChanged = onOrderChanged
                )
            }
            AnimatedVisibility(visible = tvSeries.isEmpty()) {
                FavoriteListEmptyState(
                    text = emptyStateMessage
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

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun FavoriteTVSeriesSectionPreview(
    @PreviewParameter(
        FavoriteTVSeriesSectionPreviewProvider::class
    ) tvSeries: List<TVSeries>
) {
    MovieAppTheme {
        Surface(Modifier.fillMaxSize()) {
            TVSeriesSection(
                title = "Favorite",
                emptyStateMessage = "Empty",
                tvSeries = tvSeries,
                tvOrder = ListOrder.LATEST,
                orderType = AccountScreenOrderType.FAVORITE_MOVIES,
                onOrderChanged = { _, _ -> },
                onTVSeriesClicked = {}
            )
        }
    }
}

class FavoriteTVSeriesSectionPreviewProvider : PreviewParameterProvider<List<TVSeries>> {
    override val values = sequenceOf(
        listOf(
            TVSeries(
                popularity = 632.02,
                id = 80752,
                overview = "A virus has decimated humankind. Those who survived emerged blind",
                name = "See",
                firstAirDate = "2019-11-01",
                originalName = "See",
                voteAverage = 8.3,
                posterPath = "/lKDIhc9FQibDiBQ57n3ELfZCyZg.jpg"
            )
        ),
        emptyList()
    )
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account.widget.favoritetvseries

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.ui.account.widget.favoritemovies.FavoriteItem
import com.beti1205.movieapp.ui.common.widget.listItemHorizontalPadding

@Composable
fun FavoriteTVSeriesList(
    tvSeries: List<TVSeries>,
    onTVSeriesClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyRow(
        modifier = modifier.padding(16.dp)
    ) {
        itemsIndexed(
            items = tvSeries
        ) { index, item ->
            Column(
                modifier = Modifier
                    .listItemHorizontalPadding(tvSeries, index)
                    .width(100.dp)
            ) {
                FavoriteItem(
                    poster = item.posterPath,
                    title = item.name,
                    id = item.id,
                    onItemClicked = { onTVSeriesClicked(item.id) }
                )
            }
        }
    }
}

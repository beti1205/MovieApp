/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.common.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.ui.common.widget.list.items
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.tvseries.common.PagingTVSeriesPreviewDataProvider
import kotlinx.coroutines.flow.flowOf

@Composable
fun TVSeriesList(
    tvSeriesListItems: LazyPagingItems<TVSeries>,
    onTVSeriesClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        modifier = modifier.padding(horizontal = 8.dp)
    ) {
        items(items = tvSeriesListItems) { item ->
            if (item != null) {
                TVSeriesItem(
                    tvSeries = item,
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
fun TVSeriesListPreview() {
    val items = flowOf(PagingTVSeriesPreviewDataProvider.pagingData).collectAsLazyPagingItems()

    MovieAppTheme {
        Surface {
            TVSeriesList(tvSeriesListItems = items, onTVSeriesClicked = {})
        }
    }
}

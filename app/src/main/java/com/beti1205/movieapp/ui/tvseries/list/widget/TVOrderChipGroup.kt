/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.list.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.tvseries.domain.TVOrder
import com.beti1205.movieapp.ui.movies.list.widget.OrderChip
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun TVOrderChipGroup(
    selectedTVSeriesOrder: TVOrder,
    onSelectedTVSeriesOrderChanged: (TVOrder) -> Unit,
    modifier: Modifier = Modifier,
    tvOrders: List<TVOrder> = TVOrder.availableValues()
) {
    FlowRow(modifier = modifier.padding(horizontal = 12.dp)) {
        tvOrders.forEach { order ->
            OrderChip(
                text = getTVOrderText(order = order),
                selected = order == selectedTVSeriesOrder,
                onClick = { onSelectedTVSeriesOrderChanged(order) }
            )
        }
    }
}

@Composable
fun getTVOrderText(order: TVOrder): String = stringResource(
    when (order) {
        TVOrder.POPULAR -> R.string.tv_order_popular
        TVOrder.TOP_RATED -> R.string.tv_order_top_rated
        TVOrder.AIRING_TODAY -> R.string.tv_order_airing_today
        TVOrder.ON_THE_AIR -> R.string.tv_order_on_the_air
    }
)

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun TVOrderChipGroupPreview() {
    MovieAppTheme {
        Surface {
            TVOrderChipGroup(
                selectedTVSeriesOrder = TVOrder.POPULAR,
                onSelectedTVSeriesOrderChanged = {}
            )
        }
    }
}

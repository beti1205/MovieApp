/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.list.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.movies.domain.MovieOrder
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.google.accompanist.flowlayout.FlowRow

@Composable
fun MovieOrderChipGroup(
    selectedMovieOrder: MovieOrder,
    onSelectedMovieOrderChanged: (MovieOrder) -> Unit,
    modifier: Modifier = Modifier,
    movieOrders: List<MovieOrder> = MovieOrder.availableValues()
) {
    FlowRow(modifier = modifier.padding(horizontal = 12.dp)) {
        movieOrders.forEach { order ->
            OrderChip(
                text = getMovieOrderText(order = order),
                selected = order == selectedMovieOrder,
                onClick = { onSelectedMovieOrderChanged(order) }
            )
        }
    }
}

@Composable
fun getMovieOrderText(order: MovieOrder): String = stringResource(
    when (order) {
        MovieOrder.POPULAR -> R.string.movie_order_popular
        MovieOrder.TOP_RATED -> R.string.movie_order_top_rated
        MovieOrder.UPCOMING -> R.string.movie_order_upcoming
        MovieOrder.NOW_PLAYING -> R.string.movie_order_now_playing
    }
)

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun MovieOrderChipGroupPreview() {
    MovieAppTheme {
        Surface {
            MovieOrderChipGroup(
                selectedMovieOrder = MovieOrder.POPULAR,
                onSelectedMovieOrderChanged = {}
            )
        }
    }
}

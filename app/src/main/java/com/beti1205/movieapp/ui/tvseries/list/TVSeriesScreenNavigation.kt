/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val tvSeriesScreenRoute = "tv_series"

fun NavGraphBuilder.tvSeriesScreen(
    onNavigateToTVSeriesDetails: (tvId: Int) -> Unit,
    onNavigateToTVSeriesSearch: () -> Unit
) {
    composable(tvSeriesScreenRoute) {
        TVSeriesScreen(
            onTVSeriesClicked = onNavigateToTVSeriesDetails,
            onSearchClicked = onNavigateToTVSeriesSearch
        )
    }
}

fun NavController.navigateToTVSeriesScreen() {
    this.navigate(tvSeriesScreenRoute)
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val tvSeriesSearchScreenRoute = "tv_series_search"

fun NavGraphBuilder.tvSeriesSearchScreen(
    onNavigateToTvSeriesDetails: (tvId: Int) -> Unit,
    onBackPressed: () -> Unit
) {
    composable(tvSeriesSearchScreenRoute) {
        SearchTVSeriesScreen(
            onTVSeriesClicked = onNavigateToTvSeriesDetails,
            onBackPressed = onBackPressed
        )
    }
}

fun NavController.navigateToTVSeriesSearchScreen() {
    this.navigate(tvSeriesSearchScreenRoute)
}

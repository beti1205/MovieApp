/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.beti1205.movieapp.ui.tvseries.details.TVSeriesDetailsScreen

private const val selectedTVSeriesIdArg = "selectedTVSeriesId"

internal class TVSeriesDetailsArgs(val selectedTVSeriesId: Int) {
    constructor(
        savedStateHandle: SavedStateHandle
    ) : this(checkNotNull(savedStateHandle[selectedTVSeriesIdArg]) as Int)
}

private fun NavGraphBuilder.tvSeriesDetailsScreen(
    onNavigateToPersonDetails: (Int) -> Unit,
    onNavigateToTVReviews: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    composable("tv_series_details/{$selectedTVSeriesIdArg}") {
        TVSeriesDetailsScreen(
            onPersonClicked = onNavigateToPersonDetails,
            onReviewsClicked = onNavigateToTVReviews,
            onBackPressed = onBackPressed
        )
    }
}

fun NavController.navigateToTVSeriesDetailsScreen(tvId: Int) {
    this.navigate("tv_series_details/$tvId")
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val selectedTVSeriesIdArg = "selectedTVSeriesId"
private const val tvSeriesDetailsScreenRoute = "tv_series_details"

private val arguments = listOf(
    navArgument(selectedTVSeriesIdArg) {
        type = NavType.IntType
    }
)

internal class TVSeriesDetailsArgs(val selectedTVSeriesId: Int) {
    constructor(
        savedStateHandle: SavedStateHandle
    ) : this(checkNotNull(savedStateHandle[selectedTVSeriesIdArg]) as Int)
}

fun NavGraphBuilder.tvSeriesDetailsScreen(
    onNavigateToPersonDetails: (personId: Int) -> Unit,
    onNavigateToTVReviews: (tvId: Int) -> Unit,
    onBackPressed: () -> Unit
) {
    composable(
        route = "$tvSeriesDetailsScreenRoute/{$selectedTVSeriesIdArg}",
        arguments = arguments
    ) {
        TVSeriesDetailsScreen(
            onPersonClicked = onNavigateToPersonDetails,
            onReviewsClicked = onNavigateToTVReviews,
            onBackPressed = onBackPressed
        )
    }
}

fun NavController.navigateToTVSeriesDetailsScreen(tvId: Int) {
    this.navigate("$tvSeriesDetailsScreenRoute/$tvId")
}

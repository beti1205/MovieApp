/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.reviews

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val tvIdArg = "tvSeriesId"
private const val tvSeriesReviewsScreenRoute = "tv_series_reviews"

private val arguments = listOf(
    navArgument(tvIdArg) {
        type = NavType.IntType
    }
)

internal class TVSeriesReviewsArgs(val tvId: Int) {
    constructor(
        savedStateHandle: SavedStateHandle
    ) : this(checkNotNull(savedStateHandle[tvIdArg]) as Int)
}

fun NavGraphBuilder.tvSeriesReviewsScreen(
    onBackPressed: () -> Unit
) {
    composable(
        route = "$tvSeriesReviewsScreenRoute/{$tvIdArg}",
        arguments = arguments
    ) {
        TVSeriesReviewsScreen(onBackPressed = onBackPressed)
    }
}

fun NavController.navigateToTVSeriesReviewsScreen(tvId: Int) {
    this.navigate("$tvSeriesReviewsScreenRoute/$tvId")
}

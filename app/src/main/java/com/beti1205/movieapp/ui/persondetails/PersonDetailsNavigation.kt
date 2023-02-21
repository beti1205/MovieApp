/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.persondetails

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val selectedPersonIdArg = "selectedPersonId"
private const val personDetailsScreenRoute = "person_details"

private val arguments = listOf(
    navArgument(selectedPersonIdArg) {
        type = NavType.IntType
    }
)

internal class PersonDetailsArgs(val selectedPersonId: Int) {
    constructor(
        savedStateHandle: SavedStateHandle
    ) : this(checkNotNull(savedStateHandle[selectedPersonIdArg]) as Int)
}

fun NavGraphBuilder.personDetailsScreen(
    onNavigateToMovieDetails: (movieId: Int) -> Unit,
    onNavigateToTVSeriesDetails: (tvId: Int) -> Unit,
    onBackPressed: () -> Unit
) {
    composable(
        route = "$personDetailsScreenRoute/{$selectedPersonIdArg}",
        arguments = arguments
    ) {
        PersonDetailsScreen(
            onMovieClicked = onNavigateToMovieDetails,
            onTVSeriesClicked = onNavigateToTVSeriesDetails,
            onBackPressed = onBackPressed
        )
    }
}

fun NavController.navigateToPersonDetailsScreen(personId: Int) {
    this.navigate("$personDetailsScreenRoute/$personId")
}

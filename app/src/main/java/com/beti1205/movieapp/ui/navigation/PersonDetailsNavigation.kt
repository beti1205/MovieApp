/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.beti1205.movieapp.ui.persondetails.PersonDetailsScreen

private const val selectedPersonIdArg = "selectedPersonId"

internal class PersonDetailsArgs(val selectedPersonId: Int) {
    constructor(
        savedStateHandle: SavedStateHandle
    ) : this(checkNotNull(savedStateHandle[selectedPersonIdArg]) as Int)
}

private fun NavGraphBuilder.personDetailsScreen(
    onNavigateToMovieDetails: (movieId: Int) -> Unit,
    onNavigateToTVSeriesDetails: (tvId: Int) -> Unit,
    onBackPressed: () -> Unit
) {
    composable("person_details/{$selectedPersonIdArg}") {
        PersonDetailsScreen(
            onMovieClicked = onNavigateToMovieDetails,
            onTVSeriesClicked = onNavigateToTVSeriesDetails,
            onBackPressed = onBackPressed
        )
    }
}

fun NavController.navigateToPersonDetailsScreen(personId: Int) {
    this.navigate("person_details/$personId")
}

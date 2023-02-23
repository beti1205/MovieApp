/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.details

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val selectedMovieIdArg = "selectedMovieId"
private const val movieDetailsScreenRoute = "movie_details"

private val arguments = listOf(
    navArgument(selectedMovieIdArg) {
        type = NavType.IntType
    }
)

internal class MovieDetailsArgs(val selectedMovieId: Int) {
    constructor(
        savedStateHandle: SavedStateHandle
    ) : this(checkNotNull(savedStateHandle[selectedMovieIdArg]) as Int)
}

fun NavGraphBuilder.movieDetailsScreen(
    onNavigateToPersonDetails: (personId: Int) -> Unit,
    onNavigateToMovieReviews: (movieId: Int) -> Unit,
    onBackPressed: () -> Unit
) {
    composable(
        route = "$movieDetailsScreenRoute/{$selectedMovieIdArg}",
        arguments = arguments
    ) {
        MovieDetailsScreen(
            onPersonClicked = onNavigateToPersonDetails,
            onReviewsClicked = onNavigateToMovieReviews,
            onBackPressed = onBackPressed
        )
    }
}

fun NavController.navigateToMovieDetails(movieId: Int) {
    this.navigate("$movieDetailsScreenRoute/$movieId")
}

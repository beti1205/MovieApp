/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.reviews

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument

private const val movieIdArg = "movieId"
private const val movieReviewsScreenRoute = "movie_reviews"

private val arguments = listOf(
    navArgument(movieIdArg) {
        type = NavType.IntType
    }
)

internal class MovieReviewsArgs(val movieId: Int) {
    constructor(
        savedStateHandle: SavedStateHandle
    ) : this(checkNotNull(savedStateHandle[movieIdArg]) as Int)
}

fun NavGraphBuilder.movieReviewsScreen(
    onBackPressed: () -> Unit
) {
    composable(
        route = "$movieReviewsScreenRoute/{$movieIdArg}",
        arguments = arguments
    ) {
        MovieReviewsScreen(onBackPressed = onBackPressed)
    }
}

fun NavController.navigateToMovieReviewsScreen(movieId: Int) {
    this.navigate("$movieReviewsScreenRoute/$movieId")
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.beti1205.movieapp.ui.movies.details.MovieDetailsScreen

private const val selectedMovieIdArg = "selectedMovieId"

internal class MovieDetailsArgs(val selectedMovieId: Int) {
    constructor(
        savedStateHandle: SavedStateHandle
    ) : this(checkNotNull(savedStateHandle[selectedMovieIdArg]) as Int)
}

private fun NavGraphBuilder.movieDetailsScreen(
    onNavigateToPersonDetails: (personId: Int) -> Unit,
    onNavigateToMovieReviews: (movieId: Int) -> Unit,
    onBackPressed: () -> Unit
) {
    composable("movie_details/{$selectedMovieIdArg}") {
        MovieDetailsScreen(
            onPersonClicked = onNavigateToPersonDetails,
            onReviewsClicked = onNavigateToMovieReviews,
            onBackPressed = onBackPressed
        )
    }
}

fun NavController.navigateToMovieDetails(movieId: Int) {
    this.navigate("movie_details/$movieId")
}

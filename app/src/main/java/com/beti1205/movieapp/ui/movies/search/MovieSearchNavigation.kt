/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.search

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

private const val movieSearchScreenRoute = "movie_search"

fun NavGraphBuilder.movieSearchScreen(
    onNavigateToMovieDetails: (movieId: Int) -> Unit,
    onBackPressed: () -> Unit
) {
    composable(movieSearchScreenRoute) {
        SearchMoviesScreen(
            onMovieClicked = onNavigateToMovieDetails,
            onBackPressed = onBackPressed
        )
    }
}

fun NavController.navigateToMovieSearchScreen() {
    this.navigate(movieSearchScreenRoute)
}

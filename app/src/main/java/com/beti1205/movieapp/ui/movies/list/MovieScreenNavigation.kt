/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.list

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable

const val movieScreenRoute = "movie"

fun NavGraphBuilder.movieScreen(
    onNavigateToMovieDetails: (movieId: Int) -> Unit,
    onNavigateToMovieSearch: () -> Unit
) {
    composable(movieScreenRoute) {
        MovieScreen(
            onMovieClicked = onNavigateToMovieDetails,
            onSearchClicked = onNavigateToMovieSearch
        )
    }
}

fun NavController.navigateToMovieScreen(navOptions: NavOptions? = null) {
    this.navigate(movieScreenRoute, navOptions)
}

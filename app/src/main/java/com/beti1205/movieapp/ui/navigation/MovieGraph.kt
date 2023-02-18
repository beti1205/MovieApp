/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.beti1205.movieapp.ui.movies.list.MovieScreen
import com.beti1205.movieapp.ui.movies.reviews.MovieReviewsScreen
import com.beti1205.movieapp.ui.movies.search.SearchMoviesScreen

fun NavGraphBuilder.movieGraph(navController: NavHostController) {
    composable(Screen.MovieScreen.route) {
        MovieScreen(
            onMovieClicked = { movieId ->
                navController.navigate(
                    Screen.MovieDetailsScreen.createRoute(movieId)
                )
            },
            onSearchClicked = { navController.navigate(Screen.MovieSearchScreen.route) }
        )
    }
    composable(Screen.MovieSearchScreen.route) {
        SearchMoviesScreen(
            onMovieClicked = { movieId ->
                navController.navigate(
                    Screen.MovieDetailsScreen.createRoute(movieId)
                )
            },
            onBackPressed = { navController.popBackStack() }
        )
    }
    composable(
        route = Screen.MovieReviewsScreen.route,
        arguments = Screen.MovieReviewsScreen.arguments
    ) {
        MovieReviewsScreen(onBackPressed = { navController.popBackStack() })
    }
}

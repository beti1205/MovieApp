/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.beti1205.movieapp.ui.movies.details.navigateToMovieDetails
import com.beti1205.movieapp.ui.movies.list.movieScreen
import com.beti1205.movieapp.ui.movies.list.movieScreenRoute
import com.beti1205.movieapp.ui.movies.reviews.movieReviewsScreen
import com.beti1205.movieapp.ui.movies.search.movieSearchScreen
import com.beti1205.movieapp.ui.movies.search.navigateToMovieSearchScreen

const val movieGraphRoute = "movies_graph"

fun NavGraphBuilder.movieGraph(navController: NavHostController) {
    navigation(startDestination = movieScreenRoute, route = movieGraphRoute) {
        movieScreen(
            onNavigateToMovieDetails = { movieId ->
                navController.navigateToMovieDetails(movieId)
            },
            onNavigateToMovieSearch = {
                navController.navigateToMovieSearchScreen()
            }
        )
        movieSearchScreen(
            onNavigateToMovieDetails = { movieId ->
                navController.navigateToMovieDetails(movieId)
            },
            onBackPressed = { navController.popBackStack() }
        )
        movieReviewsScreen(onBackPressed = { navController.popBackStack() })
    }
}

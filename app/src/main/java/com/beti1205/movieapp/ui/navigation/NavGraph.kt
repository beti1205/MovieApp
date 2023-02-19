/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.beti1205.movieapp.ui.account.AccountScreen
import com.beti1205.movieapp.ui.movies.details.MovieDetailsScreen
import com.beti1205.movieapp.ui.persondetails.PersonDetailsScreen
import com.beti1205.movieapp.ui.tvseries.details.TVSeriesDetailsScreen

fun NavGraphBuilder.navGraph(navController: NavHostController) {
    movieGraph(navController)
    tvSeriesGraph(navController)
    tvSeriesDetails(navController)
    account(navController)
    movieDetails(navController)
    personDetails(navController)
}

private fun NavGraphBuilder.personDetails(navController: NavHostController) {
    composable(
        route = Screen.PersonDetailsScreen.route,
        arguments = Screen.PersonDetailsScreen.arguments
    ) {
        PersonDetailsScreen(
            onBackPressed = { navController.popBackStack() },
            onMovieClicked = { movieId ->
                navController.navigate(
                    Screen.MovieDetailsScreen.createRoute(movieId)
                )
            },
            onTVSeriesClicked = { tvSeriesId ->
                navController.navigate(
                    Screen.TVSeriesDetailsScreen.createRoute(tvSeriesId)
                )
            }
        )
    }
}

private fun NavGraphBuilder.movieDetails(navController: NavHostController) {
    composable(
        route = Screen.MovieDetailsScreen.route,
        arguments = Screen.MovieDetailsScreen.arguments
    ) {
        MovieDetailsScreen(
            onPersonClicked = { personId ->
                navController.navigate(
                    Screen.PersonDetailsScreen.createRoute(personId)
                )
            },
            onReviewsClicked = { movieId ->
                navController.navigate(
                    Screen.MovieReviewsScreen.createRoute(movieId)
                )
            },
            onBackPressed = { navController.popBackStack() }
        )
    }
}

private fun NavGraphBuilder.account(navController: NavHostController) {
    composable(
        route = Screen.AccountScreen.route,
        arguments = Screen.AccountScreen.arguments,
        deepLinks = Screen.AccountScreen.deepLinks
    ) {
        AccountScreen(
            onMovieClicked = { movieId ->
                navController.navigate(
                    Screen.MovieDetailsScreen.createRoute(movieId)
                )
            },
            onTVSeriesClicked = { tvSeriesId ->
                navController.navigate(
                    Screen.TVSeriesDetailsScreen.createRoute(tvSeriesId)
                )
            }
        )
    }
}

private fun NavGraphBuilder.tvSeriesDetails(navController: NavHostController) {
    composable(
        route = Screen.TVSeriesDetailsScreen.route,
        arguments = Screen.TVSeriesDetailsScreen.arguments
    ) {
        TVSeriesDetailsScreen(
            onPersonClicked = { personId ->
                navController.navigate(
                    Screen.PersonDetailsScreen.createRoute(personId)
                )
            },
            onReviewsClicked = { tvSeriesId ->
                navController.navigate(
                    Screen.TVSeriesReviewsScreen.createRoute(tvSeriesId)
                )
            },
            onBackPressed = { navController.popBackStack() }
        )
    }
}

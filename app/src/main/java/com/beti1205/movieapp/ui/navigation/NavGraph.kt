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
    composable(Screen.AccountScreen.route) {
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

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.beti1205.movieapp.ui.account.AccountScreen

fun NavGraphBuilder.navGraph(navController: NavHostController) {
    movieGraph(navController)
    tvSeriesGraph(navController)
    account(navController)
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

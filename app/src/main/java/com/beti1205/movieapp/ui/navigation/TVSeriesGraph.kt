/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.beti1205.movieapp.ui.tvseries.list.TVSeriesScreen
import com.beti1205.movieapp.ui.tvseries.reviews.TVSeriesReviewsScreen
import com.beti1205.movieapp.ui.tvseries.search.SearchTVSeriesScreen

fun NavGraphBuilder.tvSeriesGraph(navController: NavHostController) {
    composable(Screen.TVSeriesScreen.route) {
        TVSeriesScreen(
            onTVSeriesClicked = { tvSeriesId ->
                navController.navigate(
                    Screen.TVSeriesDetailsScreen.createRoute(tvSeriesId)
                )
            },
            onSearchClicked = {
                navController.navigate(
                    Screen.TVSeriesSearchScreen.route
                )
            }
        )
    }
    composable(
        route = Screen.TVSeriesReviewsScreen.route,
        arguments = Screen.TVSeriesReviewsScreen.arguments
    ) {
        TVSeriesReviewsScreen(onBackPressed = { navController.popBackStack() })
    }
    composable(Screen.TVSeriesSearchScreen.route) {
        SearchTVSeriesScreen(
            onTVSeriesClicked = { tvSeriesId ->
                navController.navigate(
                    Screen.TVSeriesDetailsScreen.createRoute(tvSeriesId)
                )
            },
            onBackPressed = { navController.popBackStack() }
        )
    }
}

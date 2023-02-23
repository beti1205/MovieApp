/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.navigation
import com.beti1205.movieapp.ui.tvseries.details.navigateToTVSeriesDetailsScreen
import com.beti1205.movieapp.ui.tvseries.list.tvSeriesScreen
import com.beti1205.movieapp.ui.tvseries.list.tvSeriesScreenRoute
import com.beti1205.movieapp.ui.tvseries.reviews.tvSeriesReviewsScreen
import com.beti1205.movieapp.ui.tvseries.search.navigateToTVSeriesSearchScreen
import com.beti1205.movieapp.ui.tvseries.search.tvSeriesSearchScreen

const val tvSeriesGraphRoute = "tv_series_graph"

fun NavGraphBuilder.tvSeriesGraph(navController: NavHostController) {
    navigation(startDestination = tvSeriesScreenRoute, route = tvSeriesGraphRoute) {
        tvSeriesScreen(
            onNavigateToTVSeriesDetails = { tvId ->
                navController.navigateToTVSeriesDetailsScreen(tvId)
            },
            onNavigateToTVSeriesSearch = {
                navController.navigateToTVSeriesSearchScreen()
            }
        )
        tvSeriesReviewsScreen(onBackPressed = { navController.popBackStack() })
        tvSeriesSearchScreen(
            onBackPressed = { navController.popBackStack() },
            onNavigateToTvSeriesDetails = { tvId ->
                navController.navigateToTVSeriesDetailsScreen(tvId)
            }
        )
    }
}

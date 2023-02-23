/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp

import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.runtime.remember
import androidx.navigation.NavDestination
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navOptions
import com.beti1205.movieapp.ui.account.accountScreenRoute
import com.beti1205.movieapp.ui.account.navigateToAccountScreen
import com.beti1205.movieapp.ui.movies.list.movieScreenRoute
import com.beti1205.movieapp.ui.movies.list.navigateToMovieScreen
import com.beti1205.movieapp.ui.navigation.BottomNavItem
import com.beti1205.movieapp.ui.tvseries.list.navigateToTVSeriesScreen
import com.beti1205.movieapp.ui.tvseries.list.tvSeriesScreenRoute

@Stable
class AppState(
    val navController: NavHostController
) {
    private val currentDestination: NavDestination?
        @Composable get() = navController
            .currentBackStackEntryAsState().value?.destination

    val currentBottomNavItemDestination: BottomNavItem?
        @Composable get() = when (currentDestination?.route) {
            movieScreenRoute -> BottomNavItem.HOME
            tvSeriesScreenRoute -> BottomNavItem.TV_SERIES
            accountScreenRoute -> BottomNavItem.ACCOUNT
            else -> null
        }

    val bottomNavItems: List<BottomNavItem> = BottomNavItem.values().asList()

    fun navigateToBottomNavItemDestination(bottomNavItemDestination: BottomNavItem) {
        val bottomNavItemOptions = navOptions {
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }

            launchSingleTop = true
            restoreState = true
        }

        when (bottomNavItemDestination) {
            BottomNavItem.HOME -> navController.navigateToMovieScreen(bottomNavItemOptions)
            BottomNavItem.TV_SERIES -> navController.navigateToTVSeriesScreen(bottomNavItemOptions)
            BottomNavItem.ACCOUNT -> navController.navigateToAccountScreen(bottomNavItemOptions)
        }
    }

    val shouldShowBottomBar: Boolean
        @Composable get() = currentDestination?.route in listOf(
            movieScreenRoute,
            tvSeriesScreenRoute,
            accountScreenRoute
        )
}

@Composable
fun rememberAppState(
    navController: NavHostController = rememberNavController()
) = remember(navController) {
    AppState(navController)
}

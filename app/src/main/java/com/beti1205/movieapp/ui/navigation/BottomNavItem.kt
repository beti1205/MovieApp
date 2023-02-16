/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.beti1205.movieapp.Screen

enum class BottomNavItem(
    val label: String,
    val route: String,
    val icon: ImageVector
) {
    HOME(
        label = "Home",
        route = Screen.MovieScreen.route,
        icon = Icons.Default.Home
    ),
    TV_SERIES(
        label = "TV Series",
        route = Screen.TVSeriesScreen.route,
        icon = Icons.Default.Tv
    ),
    ACCOUNT(
        label = "Account",
        route = Screen.AccountScreen.route,
        icon = Icons.Default.AccountBox
    )
}

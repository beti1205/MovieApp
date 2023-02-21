/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

sealed class Screen(val route: String) {
    object MovieScreen : Screen("movie")
    object TVSeriesScreen : Screen("tv_series")
    object AccountScreen : Screen("account")
}

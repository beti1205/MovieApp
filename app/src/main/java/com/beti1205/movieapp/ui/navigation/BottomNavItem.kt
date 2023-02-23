/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Tv
import androidx.compose.ui.graphics.vector.ImageVector
import com.beti1205.movieapp.R

enum class BottomNavItem(
    @StringRes
    val label: Int,
    val icon: ImageVector
) {
    HOME(
        label = R.string.bottom_nav_home_label,
        icon = Icons.Default.Home
    ),
    TV_SERIES(
        label = R.string.bottom_nav_tv_series_label,
        icon = Icons.Default.Tv
    ),
    ACCOUNT(
        label = R.string.bottom_nav_account_label,
        icon = Icons.Default.AccountBox
    )
}

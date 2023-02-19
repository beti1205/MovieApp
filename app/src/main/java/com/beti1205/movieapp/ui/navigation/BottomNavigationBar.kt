/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    currentRoute: String,
    navigateToRoute: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.secondary
    ) {
        items.forEach { item ->
            BottomNavigationItem(
                selected = currentRoute == item.route,
                onClick = { navigateToRoute(item.route) },
                label = {
                    Text(text = item.label)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = null)
                }
            )
        }
    }
}

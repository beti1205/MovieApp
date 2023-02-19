/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.beti1205.movieapp.ui.navigation.BottomNavigationBar
import com.beti1205.movieapp.ui.navigation.Graph
import com.beti1205.movieapp.ui.navigation.navGraph
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieAppTheme {
                val appState = rememberAppState()
                val navController = appState.navController

                Scaffold(bottomBar = {
                    if (appState.shouldShowBottomBar) {
                        BottomNavigationBar(
                            items = appState.bottomBarTabs,
                            currentRoute = appState.currentRoute!!,
                            navigateToRoute = appState::navigateToBottomBarRoute
                        )
                    }
                }) { paddingValues ->
                    NavHost(
                        navController = appState.navController,
                        startDestination = Graph.MoviesGraph.route,
                        modifier = Modifier.padding(paddingValues = paddingValues)
                    ) {
                        navGraph(navController)
                    }
                }
            }
        }
    }
}

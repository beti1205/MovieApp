/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Tv
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.beti1205.movieapp.ui.movies.details.MovieDetailsScreen
import com.beti1205.movieapp.ui.movies.list.MovieScreen
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @SuppressLint("UnusedMaterialScaffoldPaddingParameter")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MovieAppTheme {
                val navController = rememberNavController()
                Scaffold(bottomBar = {
                    BottomNavigationBar(navController = navController)
                }) {
                    NavHost(
                        navController = navController,
                        startDestination = Screen.MovieScreen.route
                    ) {
                        composable(Screen.MovieScreen.route) {
                            MovieScreen(
                                onMovieClicked = { movieId ->
                                    navController.navigate(
                                        Screen.MovieDetailsScreen.withArgs(
                                            movieId.toString()
                                        )
                                    )
                                },
                                onSearchClicked = { navController.navigate(Screen.MovieSearchScreen.route) }
                            )
                        }
                        composable(Screen.TVScreen.route) {
                            TVScreen()
                        }
                        composable(Screen.AccountScreen.route) {
                            AccountScreen()
                        }
                        composable(
                            route = "${Screen.MovieDetailsScreen.route}/{selectedMovieId}",
                            arguments = listOf(
                                navArgument("selectedMovieId") {
                                    type = NavType.IntType
                                }
                            )
                        ) {
                            MovieDetailsScreen(onBackPressed = { navController.popBackStack() })
                        }
                        composable(Screen.MovieSearchScreen.route) {
                            MovieSearchScreen()
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun BottomNavigationBar(navController: NavHostController) {
    BottomNavigationBar(
        items = listOf(
            BottomNavItem(
                name = "Home",
                route = Screen.MovieScreen.route,
                icon = Icons.Default.Home
            ),
            BottomNavItem(
                name = "TV Series",
                route = Screen.TVScreen.route,
                icon = Icons.Default.Tv
            ),
            BottomNavItem(
                name = "Account",
                route = Screen.AccountScreen.route,
                icon = Icons.Default.AccountBox
            )

        ),
        navController = navController,
        onItemCLick = { navController.navigate(it.route) }
    )
}

@Composable
fun MovieSearchScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Movie Details Screen")
    }
}

@Composable
fun TVScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "TV Screen")
    }
}

@Composable
fun AccountScreen() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Text(text = "Account Screen")
    }
}

data class BottomNavItem(
    val name: String,
    val route: String,
    val icon: ImageVector
)

@Composable
fun BottomNavigationBar(
    items: List<BottomNavItem>,
    navController: NavController,
    modifier: Modifier = Modifier,
    onItemCLick: (BottomNavItem) -> Unit
) {
    val backStackEntry = navController.currentBackStackEntryAsState()
    BottomNavigation(
        modifier = modifier,
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 5.dp
    ) {
        items.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route
            BottomNavigationItem(
                selected = selected,
                onClick = { onItemCLick(item) },
                selectedContentColor = Color.White,
                unselectedContentColor = Color.Gray,
                icon = {
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Icon(imageVector = item.icon, contentDescription = null)
                        Text(text = item.name)
                    }
                }
            )
        }
    }
}

sealed class Screen(val route: String) {
    object MovieScreen : Screen("movie")
    object MovieDetailsScreen : Screen("movie_details")
    object MovieSearchScreen : Screen("movie_search")
    object TVScreen : Screen("tv_series")
    object AccountScreen : Screen("account")

    fun withArgs(vararg args: String): String {
        return buildString {
            append(route)
            args.forEach { arg ->
                append("/$arg")
            }
        }
    }
}

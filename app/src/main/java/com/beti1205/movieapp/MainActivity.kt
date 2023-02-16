/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.beti1205.movieapp.ui.movies.details.MovieDetailsScreen
import com.beti1205.movieapp.ui.movies.list.MovieScreen
import com.beti1205.movieapp.ui.movies.reviews.MovieReviewsScreen
import com.beti1205.movieapp.ui.movies.search.SearchMoviesScreen
import com.beti1205.movieapp.ui.navigation.BottomNavigationBar
import com.beti1205.movieapp.ui.persondetails.PersonDetailsScreen
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
                        startDestination = Screen.MovieScreen.route,
                        modifier = Modifier.padding(paddingValues = paddingValues)
                    ) {
                        movieGraph(navController)
                        composable(Screen.TVSeriesScreen.route) {
                            TVScreen()
                        }
                        composable(Screen.AccountScreen.route) {
                            AccountScreen()
                        }
                        composable(
                            route = Screen.MovieDetailsScreen.route,
                            arguments = Screen.MovieDetailsScreen.arguments
                        ) {
                            MovieDetailsScreen(
                                onPersonClicked = { personId ->
                                    navController.navigate(
                                        Screen.PersonDetailsScreen.createRoute(personId)
                                    )
                                },
                                onReviewsClicked = { movieId ->
                                    navController.navigate(
                                        Screen.MovieReviewsScreen.createRoute(movieId)
                                    )
                                },
                                onBackPressed = { navController.popBackStack() }
                            )
                        }
                        composable(
                            route = Screen.PersonDetailsScreen.route,
                            arguments = Screen.PersonDetailsScreen.arguments
                        ) {
                            PersonDetailsScreen(
                                onBackPressed = { navController.popBackStack() },
                                onMovieClicked = { movieId ->
                                    navController.navigate(
                                        Screen.MovieDetailsScreen.createRoute(movieId)
                                    )
                                }
                            )
                        }
                    }
                }
            }
        }
    }

    private fun NavGraphBuilder.movieGraph(navController: NavHostController) {
        composable(Screen.MovieScreen.route) {
            MovieScreen(
                onMovieClicked = { movieId ->
                    navController.navigate(
                        Screen.MovieDetailsScreen.createRoute(movieId)
                    )
                },
                onSearchClicked = { navController.navigate(Screen.MovieSearchScreen.route) }
            )
        }
        composable(Screen.MovieSearchScreen.route) {
            SearchMoviesScreen(
                onMovieClicked = { movieId ->
                    navController.navigate(
                        Screen.MovieDetailsScreen.createRoute(movieId)
                    )
                },
                onBackPressed = { navController.popBackStack() }
            )
        }
        composable(
            route = Screen.MovieReviewsScreen.route,
            arguments = Screen.MovieReviewsScreen.arguments
        ) {
            MovieReviewsScreen(onBackPressed = { navController.popBackStack() })
        }
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

sealed class Screen(val route: String) {
    object MovieScreen : Screen("movie")
    object MovieDetailsScreen : Screen("movie_details/{selectedMovieId}") {
        val arguments = listOf(
            navArgument("selectedMovieId") {
                type = NavType.IntType
            }
        )

        fun createRoute(selectedMovieId: Int) = "movie_details/$selectedMovieId"
    }

    object MovieSearchScreen : Screen("movie_search")
    object MovieReviewsScreen : Screen("movie_reviews/{movieId}") {
        val arguments = listOf(
            navArgument("movieId") {
                type = NavType.IntType
            }
        )

        fun createRoute(movieId: Int) = "movie_reviews/$movieId"
    }

    object PersonDetailsScreen : Screen("person_details/{selectedPersonId}") {
        val arguments = listOf(
            navArgument("selectedPersonId") {
                type = NavType.IntType
            }
        )

        fun createRoute(selectedPersonId: Int) = "person_details/$selectedPersonId"
    }

    object TVSeriesScreen : Screen("tv_series")
    object TVSeriesDetailsScreen : Screen("tv_series_details")
    object TVSeriesReviewsScreen : Screen("tv_series_reviews")
    object SearchTVSeriesScreen : Screen("tv_series_search")
    object AccountScreen : Screen("account")
}

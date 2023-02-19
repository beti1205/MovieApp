/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

import androidx.navigation.NavType
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink

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
    object TVSeriesDetailsScreen : Screen("tv_series_details/{selectedTVSeriesId}") {
        val arguments = listOf(
            navArgument("selectedTVSeriesId") {
                type = NavType.IntType
            }
        )

        fun createRoute(selectedTVSeriesId: Int) = "tv_series_details/$selectedTVSeriesId"
    }

    object TVSeriesReviewsScreen : Screen("tv_series_reviews/{tvSeriesId}") {
        val arguments = listOf(
            navArgument("tvSeriesId") {
                type = NavType.IntType
            }
        )

        fun createRoute(tvSeriesId: Int) = "tv_series_reviews/$tvSeriesId"
    }

    object TVSeriesSearchScreen : Screen("tv_series_search")
    object AccountScreen : Screen("account") {
        private const val uri = "movieapp://app/authenticate"
        val deepLinks = listOf(
            navDeepLink {
                uriPattern = "$uri?request_token={request_token}&approved={approved}" +
                    "&denied={denied}"
            }
        )
        val arguments = listOf(
            navArgument("request_token") {
                type = NavType.StringType
                nullable = true
            },
            navArgument("approved") {
                type = NavType.BoolType
                defaultValue = false
            },
            navArgument("denied") {
                type = NavType.BoolType
                defaultValue = false
            }
        )
    }
}

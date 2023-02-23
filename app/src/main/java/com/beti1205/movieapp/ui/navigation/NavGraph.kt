/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.beti1205.movieapp.ui.account.accountScreen
import com.beti1205.movieapp.ui.movies.details.movieDetailsScreen
import com.beti1205.movieapp.ui.movies.details.navigateToMovieDetails
import com.beti1205.movieapp.ui.movies.reviews.navigateToMovieReviewsScreen
import com.beti1205.movieapp.ui.persondetails.navigateToPersonDetailsScreen
import com.beti1205.movieapp.ui.persondetails.personDetailsScreen
import com.beti1205.movieapp.ui.tvseries.details.navigateToTVSeriesDetailsScreen
import com.beti1205.movieapp.ui.tvseries.details.tvSeriesDetailsScreen
import com.beti1205.movieapp.ui.tvseries.reviews.navigateToTVSeriesReviewsScreen

fun NavGraphBuilder.navGraph(navController: NavHostController) {
    movieGraph(navController)
    tvSeriesGraph(navController)

    movieDetailsScreen(
        onNavigateToPersonDetails = { personId ->
            navController.navigateToPersonDetailsScreen(personId)
        },
        onNavigateToMovieReviews = { movieId ->
            navController.navigateToMovieReviewsScreen(movieId)
        },
        onBackPressed = { navController.popBackStack() }
    )
    tvSeriesDetailsScreen(
        onNavigateToPersonDetails = { personId ->
            navController.navigateToPersonDetailsScreen(personId)
        },
        onNavigateToTVReviews = { tvId ->
            navController.navigateToTVSeriesReviewsScreen(tvId)
        },
        onBackPressed = { navController.popBackStack() }
    )
    personDetailsScreen(
        onNavigateToMovieDetails = { movieId ->
            navController.navigateToMovieDetails(movieId)
        },
        onNavigateToTVSeriesDetails = { tvId ->
            navController.navigateToTVSeriesDetailsScreen(tvId)
        },
        onBackPressed = { navController.popBackStack() }
    )
    accountScreen(
        onNavigateToMovieDetails = { movieId ->
            navController.navigateToMovieDetails(movieId)
        },
        onNavigateToTVSeriesDetails = { tvId ->
            navController.navigateToTVSeriesDetailsScreen(tvId)
        }
    )
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account

import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navDeepLink
import kotlinx.coroutines.flow.StateFlow

const val accountScreenRoute = "account"

private const val requestTokenArg = "request_token"
private const val approvedArg = "approved"
private const val deniedArg = "denied"
private const val uri = "movieapp://app/authenticate"

private val deepLinks = listOf(
    navDeepLink {
        uriPattern = "$uri?request_token={request_token}&approved={approved}&denied={denied}"
    }
)

private val arguments = listOf(
    navArgument(requestTokenArg) {
        type = NavType.StringType
        nullable = true
    },
    navArgument(approvedArg) {
        type = NavType.BoolType
        defaultValue = false
    },
    navArgument(deniedArg) {
        type = NavType.BoolType
        defaultValue = false
    }
)

internal class AccountScreenArgs(
    val requestToken: StateFlow<String?>,
    val approved: StateFlow<Boolean>,
    val denied: StateFlow<Boolean>
) {
    constructor(
        savedStateHandle: SavedStateHandle
    ) : this(
        requestToken = savedStateHandle.getStateFlow(requestTokenArg, null),
        approved = savedStateHandle.getStateFlow(approvedArg, false),
        denied = savedStateHandle.getStateFlow(deniedArg, false)
    )
}

fun NavGraphBuilder.accountScreen(
    onNavigateToMovieDetails: (movieId: Int) -> Unit,
    onNavigateToTVSeriesDetails: (tvId: Int) -> Unit
) {
    composable(
        route = accountScreenRoute,
        arguments = arguments,
        deepLinks = deepLinks
    ) {
        AccountScreen(
            onMovieClicked = onNavigateToMovieDetails,
            onTVSeriesClicked = onNavigateToTVSeriesDetails
        )
    }
}

fun NavController.navigateToAccountScreen(navOptions: NavOptions? = null) {
    this.navigate(accountScreenRoute, navOptions)
}

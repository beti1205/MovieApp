/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.details

import android.content.res.Configuration
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.common.widget.Error
import com.beti1205.movieapp.ui.common.widget.Loader
import com.beti1205.movieapp.ui.common.widget.TopAppBar
import com.beti1205.movieapp.ui.movies.details.widget.MovieDetails
import com.beti1205.movieapp.ui.movies.details.widget.MovieDetailsScreenPreviewProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel = hiltViewModel(),
    onPersonClicked: (Int) -> Unit,
    onReviewsClicked: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    MovieDetailsScreen(
        state = state,
        isLoggedIn = isLoggedIn,
        onWatchlistErrorHandled = viewModel::onWatchlistErrorHandled,
        onFavoriteErrorHandled = viewModel::onFavoriteErrorHandled,
        onFavoriteClicked = viewModel::markFavorite,
        onWatchlistIconClicked = viewModel::addToWatchlist,
        onPersonClicked = onPersonClicked,
        onReviewsClicked = onReviewsClicked,
        onBackPressed = onBackPressed
    )
}

@Composable
fun MovieDetailsScreen(
    state: MovieDetailsScreenState,
    isLoggedIn: Boolean,
    onWatchlistErrorHandled: () -> Unit,
    onFavoriteErrorHandled: () -> Unit,
    onFavoriteClicked: (Boolean) -> Unit,
    onWatchlistIconClicked: (Boolean) -> Unit,
    onPersonClicked: (Int) -> Unit,
    onReviewsClicked: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    val favoriteErrorMessage = stringResource(R.string.movie_details_favorite_error)
    val watchlistErrorMessage = stringResource(R.string.movie_details_watchlist_error)

    MovieDetailsScreenContent(
        scaffoldState = scaffoldState,
        scrollState = scrollState,
        state = state,
        isLoggedIn = isLoggedIn,
        onBackPressed = onBackPressed,
        onPersonClicked = onPersonClicked,
        onReviewsClicked = onReviewsClicked,
        onFavoriteClicked = onFavoriteClicked,
        onWatchlistIconClicked = onWatchlistIconClicked
    )

    if (state.favoriteHasError) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = favoriteErrorMessage
            )
            onFavoriteErrorHandled()
        }
    }

    if (state.watchlistError) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = watchlistErrorMessage
            )
            onWatchlistErrorHandled()
        }
    }
}

@Composable
private fun MovieDetailsScreenContent(
    scaffoldState: ScaffoldState,
    scrollState: ScrollState,
    state: MovieDetailsScreenState,
    isLoggedIn: Boolean,
    onBackPressed: () -> Unit,
    onPersonClicked: (Int) -> Unit,
    onReviewsClicked: (Int) -> Unit,
    onFavoriteClicked: (Boolean) -> Unit,
    onWatchlistIconClicked: (Boolean) -> Unit
) {
    MovieAppTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                TopAppBar(
                    title = stringResource(id = R.string.movie_details_label),
                    onBackPressed = onBackPressed
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                DefaultAnimatedVisibility(visible = state.hasError) {
                    Error()
                }
                DefaultAnimatedVisibility(visible = state.isLoading) {
                    Loader()
                }
                DefaultAnimatedVisibility(visible = !state.hasError && !state.isLoading) {
                    MovieDetails(
                        movieDetails = state.movieDetails,
                        credits = state.credits,
                        favorite = state.favorite,
                        watchlist = state.watchlist,
                        isLoggedIn = isLoggedIn,
                        onPersonClicked = onPersonClicked,
                        onReviewsClicked = onReviewsClicked,
                        onFavoriteClicked = onFavoriteClicked,
                        onWatchlistIconClicked = onWatchlistIconClicked,
                        modifier = Modifier.verticalScroll(scrollState)
                    )
                }
            }
        }
    }
}

@Composable
private fun DefaultAnimatedVisibility(
    visible: Boolean,
    modifier: Modifier = Modifier,
    enter: EnterTransition = fadeIn(),
    exit: ExitTransition = fadeOut(),
    label: String = "AnimatedVisibility",
    content: @Composable AnimatedVisibilityScope.() -> Unit
) {
    AnimatedVisibility(
        visible = visible,
        modifier = modifier,
        enter = enter,
        exit = exit,
        label = label,
        content = content
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    heightDp = 2000
)
@Composable
fun MovieDetailsScreenPreview(
    @PreviewParameter(MovieDetailsScreenPreviewProvider::class)
    state: MovieDetailsScreenState
) {
    MovieAppTheme {
        MovieDetailsScreen(
            state = MovieDetailsScreenState(
                movieDetails = state.movieDetails,
                credits = state.credits,
                hasError = state.hasError
            ),
            isLoggedIn = true,
            onWatchlistErrorHandled = {},
            onFavoriteErrorHandled = {},
            onFavoriteClicked = {},
            onWatchlistIconClicked = {},
            onPersonClicked = {},
            onReviewsClicked = {},
            onBackPressed = {}
        )
    }
}

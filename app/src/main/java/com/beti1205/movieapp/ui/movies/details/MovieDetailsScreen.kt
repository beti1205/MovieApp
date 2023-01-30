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
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.common.widget.Error
import com.beti1205.movieapp.ui.common.widget.Loader
import com.beti1205.movieapp.ui.common.widget.TopAppBar
import com.beti1205.movieapp.ui.movies.details.widget.MovieDetails
import com.beti1205.movieapp.ui.movies.details.widget.MovieDetailsScreenPreviewProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieDetailsScreen(
    viewModel: MovieDetailsViewModel,
    onPersonClicked: (Int) -> Unit,
    onButtonClicked: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val state by viewModel.state.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()

    MovieDetailsScreen(
        state = state,
        isLoggedIn = isLoggedIn,
        onFavoriteErrorHandled = viewModel::onFavoriteErrorHandled,
        onFavoriteClicked = viewModel::markFavorite,
        onPersonClicked = onPersonClicked,
        onButtonClicked = onButtonClicked,
        onBackPressed = onBackPressed
    )
}

@Composable
fun MovieDetailsScreen(
    state: MovieDetailsScreenState,
    isLoggedIn: Boolean,
    onFavoriteErrorHandled: () -> Unit,
    onFavoriteClicked: (Boolean) -> Unit,
    onPersonClicked: (Int) -> Unit,
    onButtonClicked: (Int) -> Unit,
    onBackPressed: () -> Unit
) {
    val scrollState = rememberScrollState()
    val scaffoldState = rememberScaffoldState()
    val favoriteErrorMessage = stringResource(R.string.movie_details_favorite_error)

    MovieDetailsScreenContent(
        scaffoldState,
        onBackPressed,
        state,
        isLoggedIn,
        onPersonClicked,
        onButtonClicked,
        onFavoriteClicked,
        scrollState
    )

    if (state.favoriteHasError) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = favoriteErrorMessage
            )
            onFavoriteErrorHandled()
        }
    }
}

@Composable
private fun MovieDetailsScreenContent(
    scaffoldState: ScaffoldState,
    onBackPressed: () -> Unit,
    state: MovieDetailsScreenState,
    isLoggedIn: Boolean,
    onPersonClicked: (Int) -> Unit,
    onButtonClicked: (Int) -> Unit,
    onFavoriteClicked: (Boolean) -> Unit,
    scrollState: ScrollState
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
                        isLoggedIn = isLoggedIn,
                        onPersonClicked = onPersonClicked,
                        onButtonClicked = onButtonClicked,
                        onFavoriteClicked = onFavoriteClicked,
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
            onFavoriteErrorHandled = {},
            onFavoriteClicked = {},
            onPersonClicked = {},
            onButtonClicked = {},
            onBackPressed = {}
        )
    }
}

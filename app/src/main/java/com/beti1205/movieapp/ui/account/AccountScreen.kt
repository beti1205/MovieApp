/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner
import com.beti1205.movieapp.R
import com.beti1205.movieapp.common.ListOrder
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.ui.account.widget.AccountScreenContent
import com.beti1205.movieapp.ui.account.widget.dialog.AccountDialog
import com.beti1205.movieapp.ui.account.widget.preview.AccountScreenData
import com.beti1205.movieapp.ui.account.widget.preview.AccountScreenPreviewProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun AccountScreen(
    viewModel: AccountViewModel = hiltViewModel(),
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onMovieClicked: (Int) -> Unit,
    onTVSeriesClicked: (Int) -> Unit
) {
    val authUri by viewModel.authUri.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val hasError by viewModel.hasError.collectAsState()
    val denied by viewModel.denied.collectAsState()
    val account by viewModel.account.collectAsState()
    val movies by viewModel.movies.collectAsState()
    val tvSeries by viewModel.tvSeries.collectAsState()
    val movieWatchlist by viewModel.movieWatchlist.collectAsState()
    val tvSeriesWatchlist by viewModel.tvSeriesWatchlist.collectAsState()
    val favoriteMoviesOrder by viewModel.favoriteMoviesOrder.collectAsState()
    val favoriteTVOrder by viewModel.favoriteTVOrder.collectAsState()
    val movieWatchlistOrder by viewModel.movieWatchlistOrder.collectAsState()
    val tvSeriesWatchlistOrder by viewModel.tvSeriesWatchlistOrder.collectAsState()
    val context = LocalContext.current
    val currentOnResume by rememberUpdatedState(newValue = viewModel::fetchData)

    AccountScreen(
        isLoggedIn = isLoggedIn,
        hasError = hasError,
        denied = denied,
        account = account,
        tvSeries = tvSeries,
        movies = movies,
        movieWatchlist = movieWatchlist,
        tvSeriesWatchlist = tvSeriesWatchlist,
        favoriteMoviesOrder = favoriteMoviesOrder,
        favoriteTVOrder = favoriteTVOrder,
        movieWatchlistOrder = movieWatchlistOrder,
        tvSeriesWatchlistOrder = tvSeriesWatchlistOrder,
        onFavoriteMoviesOrderChanged = viewModel::onFavoriteMoviesOrderChanged,
        onFavoriteTVOrderChanged = viewModel::onFavoriteTVOrderChanged,
        onWatchlistOrderChanged = viewModel::onMovieWatchlistOrderChanged,
        onTVSeriesWatchlistOrderChanged = viewModel::onTVSeriesWatchlistOrderChanged,
        onLoginClicked = viewModel::fetchRequestToken,
        onErrorHandled = viewModel::onErrorHandled,
        onDeniedHandled = viewModel::onDeniedHandled,
        onDeleteSession = viewModel::deleteSession,
        onMovieClicked = onMovieClicked,
        onTVSeriesClicked = onTVSeriesClicked
    )

    LaunchedEffect(authUri) {
        if (authUri == null) {
            return@LaunchedEffect
        }

        val intent = Intent(Intent.ACTION_VIEW, authUri)
        context.startActivity(intent)
    }

    DisposableEffect(lifecycleOwner) {
        val observer = LifecycleEventObserver { _, event ->
            if (event == Lifecycle.Event.ON_RESUME) {
                currentOnResume()
            }
        }
        lifecycleOwner.lifecycle.addObserver(observer)

        onDispose {
            lifecycleOwner.lifecycle.removeObserver(observer)
        }
    }
}

@Composable
fun AccountScreen(
    isLoggedIn: Boolean,
    hasError: Boolean,
    denied: Boolean,
    account: AccountDetails?,
    movies: List<Movie>,
    movieWatchlist: List<Movie>,
    tvSeries: List<TVSeries>,
    tvSeriesWatchlist: List<TVSeries>,
    favoriteMoviesOrder: ListOrder,
    favoriteTVOrder: ListOrder,
    movieWatchlistOrder: ListOrder,
    tvSeriesWatchlistOrder: ListOrder,
    onFavoriteMoviesOrderChanged: (ListOrder) -> Unit,
    onFavoriteTVOrderChanged: (ListOrder) -> Unit,
    onWatchlistOrderChanged: (ListOrder) -> Unit,
    onTVSeriesWatchlistOrderChanged: (ListOrder) -> Unit,
    onLoginClicked: () -> Unit,
    onErrorHandled: () -> Unit,
    onDeniedHandled: () -> Unit,
    onDeleteSession: () -> Unit,
    onMovieClicked: (Int) -> Unit,
    onTVSeriesClicked: (Int) -> Unit
) {
    var isDialogVisible by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val errorMessage = stringResource(id = R.string.generic_error_message)
    val deniedMessage = stringResource(id = R.string.denied_login_message)

    AccountScreenContent(
        scaffoldState = scaffoldState,
        isLoggedIn = isLoggedIn,
        account = account,
        movies = movies,
        tvSeries = tvSeries,
        tvSeriesWatchlist = tvSeriesWatchlist,
        favoriteMoviesOrder = favoriteMoviesOrder,
        favoriteTVOrder = favoriteTVOrder,
        movieWatchlist = movieWatchlist,
        movieWatchlistOrder = movieWatchlistOrder,
        tvSeriesWatchlistOrder = tvSeriesWatchlistOrder,
        onWatchlistOrderChanged = onWatchlistOrderChanged,
        onTVSeriesWatchlistOrderChanged = onTVSeriesWatchlistOrderChanged,
        onFavoriteMoviesOrderChanged = onFavoriteMoviesOrderChanged,
        onFavoriteTVOrderChanged = onFavoriteTVOrderChanged,
        onLoginClicked = onLoginClicked,
        onMovieClicked = onMovieClicked,
        onTVSeriesClicked = onTVSeriesClicked,
        onAvatarClicked = { isDialogVisible = !isDialogVisible }
    )

    if (isLoggedIn && isDialogVisible && account != null) {
        AccountDialog(
            account = account,
            onDeleteSession = onDeleteSession,
            onDismissRequest = { isDialogVisible = false }
        )
    }

    if (denied) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = deniedMessage
            )

            onDeniedHandled()
        }
    }

    if (hasError) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = errorMessage
            )

            onErrorHandled()
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun AccountScreenPreview(
    @PreviewParameter(AccountScreenPreviewProvider::class) data: AccountScreenData
) {
    MovieAppTheme {
        AccountScreen(
            isLoggedIn = data.isLoggedIn,
            hasError = data.hasError,
            denied = data.denied,
            account = data.account,
            movies = data.movies,
            movieWatchlist = data.movies,
            tvSeries = data.tvSeries,
            tvSeriesWatchlist = data.tvSeries,
            favoriteMoviesOrder = ListOrder.LATEST,
            favoriteTVOrder = ListOrder.LATEST,
            movieWatchlistOrder = ListOrder.LATEST,
            tvSeriesWatchlistOrder = ListOrder.LATEST,
            onFavoriteMoviesOrderChanged = {},
            onFavoriteTVOrderChanged = {},
            onWatchlistOrderChanged = {},
            onTVSeriesWatchlistOrderChanged = {},
            onLoginClicked = {},
            onErrorHandled = {},
            onDeniedHandled = {},
            onDeleteSession = {},
            onMovieClicked = {},
            onTVSeriesClicked = {}
        )
    }
}

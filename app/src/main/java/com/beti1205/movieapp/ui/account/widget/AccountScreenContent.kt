/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account.widget

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.common.ListOrder
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.ui.account.widget.favoritemovies.MoviesSection
import com.beti1205.movieapp.ui.account.widget.favoritetvseries.FavoriteTVSeriesSection
import com.beti1205.movieapp.ui.account.widget.topappbar.AccountTopAppBar
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun AccountScreenContent(
    scaffoldState: ScaffoldState,
    isLoggedIn: Boolean,
    account: AccountDetails?,
    movies: List<Movie>,
    movieWatchlist: List<Movie>,
    tvSeries: List<TVSeries>,
    favoriteMoviesOrder: ListOrder,
    favoriteTVOrder: ListOrder,
    movieWatchlistOrder: ListOrder,
    onFavoriteMoviesOrderChanged: (ListOrder) -> Unit,
    onFavoriteTVOrderChanged: (ListOrder) -> Unit,
    onWatchlistOrderChanged: (ListOrder) -> Unit,
    onLoginClicked: () -> Unit,
    onMovieClicked: (Int) -> Unit,
    onTVSeriesClicked: (Int) -> Unit,
    onAvatarClicked: () -> Unit
) {
    MovieAppTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                AccountTopAppBar(
                    account = account,
                    isLoggedIn = isLoggedIn,
                    onAvatarClicked = onAvatarClicked
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (isLoggedIn) {
                    Column(
                        modifier = Modifier
                            .verticalScroll(rememberScrollState())
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        MoviesSection(
                            title = stringResource(R.string.favorite_movies_section_header),
                            emptyStateMessage = stringResource(
                                R.string.favorite_movies_empty_state_message
                            ),
                            movies = movies,
                            moviesOrder = favoriteMoviesOrder,
                            onMoviesListOrderChanged = onFavoriteMoviesOrderChanged,
                            onMovieClicked = onMovieClicked
                        )
                        FavoriteTVSeriesSection(
                            tvSeries = tvSeries,
                            favoriteTVOrder = favoriteTVOrder,
                            onFavoriteTVOrderChanged = onFavoriteTVOrderChanged,
                            onTVSeriesClicked = onTVSeriesClicked
                        )
                        MoviesSection(
                            title = stringResource(id = R.string.movie_watchlist_section_header),
                            emptyStateMessage = stringResource(
                                id = R.string.movie_watchlist_empty_state_message
                            ),
                            movies = movieWatchlist,
                            moviesOrder = movieWatchlistOrder,
                            onMoviesListOrderChanged = onWatchlistOrderChanged,
                            onMovieClicked = onMovieClicked
                        )
                    }
                } else {
                    LoginButton(
                        onLoginClicked = onLoginClicked,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

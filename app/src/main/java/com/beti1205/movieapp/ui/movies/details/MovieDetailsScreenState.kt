/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.details

import com.beti1205.movieapp.feature.credits.data.Credits
import com.beti1205.movieapp.feature.moviedetails.data.MovieDetails

data class MovieDetailsScreenState(
    val movieDetails: MovieDetails? = null,
    val favorite: Boolean = false,
    val credits: Credits? = null,
    val watchlist: Boolean = false,
    val hasError: Boolean = false,
    val favoriteHasError: Boolean = false,
    val watchlistError: Boolean = false,
    val isLoading: Boolean = false
)

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.MediaType
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.combinedmoviedetails.FetchCombinedMovieDetailsUseCase
import com.beti1205.movieapp.feature.favorite.domain.MarkFavoriteUseCase
import com.beti1205.movieapp.feature.watchlist.domain.AddToWatchlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val fetchCombinedMovieDetailsUseCase: FetchCombinedMovieDetailsUseCase,
    private val markFavoriteUseCase: MarkFavoriteUseCase,
    private val addToWatchlistUseCase: AddToWatchlistUseCase,
    private val authManager: AuthManager
) : ViewModel() {

    private val movieDetailsArgs = MovieDetailsArgs(state)

    private val _state = MutableStateFlow(MovieDetailsScreenState())
    val state: StateFlow<MovieDetailsScreenState> = _state.asStateFlow()

    val isLoggedIn = authManager.isLoggedInFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        false
    )

    init {
        fetchMovieDetails(movieDetailsArgs.selectedMovieId)
    }

    private fun fetchMovieDetails(id: Int) {
        viewModelScope.launch {
            _state.value = MovieDetailsScreenState(isLoading = true)

            val result = fetchCombinedMovieDetailsUseCase(id)

            when (result) {
                is Result.Error -> _state.value = MovieDetailsScreenState(hasError = true)
                is Result.Success -> _state.value = result.data
            }
        }
    }

    fun markFavorite(favorite: Boolean) {
        viewModelScope.launch {
            if (!authManager.isLoggedIn) {
                return@launch
            }

            _state.value = _state.value.copy(favorite = favorite)

            val result = markFavoriteUseCase(
                favorite = favorite,
                mediaType = MediaType.MOVIE,
                mediaId = movieDetailsArgs.selectedMovieId
            )

            if (result is Result.Error) {
                _state.value = _state.value.copy(favorite = !favorite)
                _state.value = _state.value.copy(favoriteHasError = true)
            }
        }
    }

    fun addToWatchlist(watchlist: Boolean) {
        viewModelScope.launch {
            if (!authManager.isLoggedIn) {
                return@launch
            }

            _state.value = _state.value.copy(watchlist = watchlist)

            val result = addToWatchlistUseCase(
                watchlist = watchlist,
                mediaType = MediaType.MOVIE,
                mediaId = movieDetailsArgs.selectedMovieId
            )

            if (result is Result.Error) {
                _state.value = _state.value.copy(watchlist = !watchlist)
                _state.value = _state.value.copy(watchlistError = true)
            }
        }
    }

    fun onFavoriteErrorHandled() {
        _state.value = _state.value.copy(favoriteHasError = false)
    }

    fun onWatchlistErrorHandled() {
        _state.value = _state.value.copy(watchlistError = false)
    }
}

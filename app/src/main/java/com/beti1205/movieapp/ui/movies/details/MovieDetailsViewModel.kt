/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.flatZip
import com.beti1205.movieapp.feature.accountstates.data.AccountStates
import com.beti1205.movieapp.feature.accountstates.domain.FetchMoviesAccountStatesUseCase
import com.beti1205.movieapp.feature.credits.data.Credits
import com.beti1205.movieapp.feature.credits.domain.FetchMovieCreditsUseCase
import com.beti1205.movieapp.feature.favorite.domain.MarkFavoriteUseCase
import com.beti1205.movieapp.feature.favorite.domain.MediaType
import com.beti1205.movieapp.feature.moviedetails.data.MovieDetails
import com.beti1205.movieapp.feature.moviedetails.domain.FetchMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val fetchMovieCreditsUseCase: FetchMovieCreditsUseCase,
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase,
    private val markFavoriteUseCase: MarkFavoriteUseCase,
    private val fetchMoviesAccountStatesUseCase: FetchMoviesAccountStatesUseCase,
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

            val movieDetailsDeferredResult = async {
                fetchMovieDetailsUseCase(id)
            }
            val creditDeferredResult = async {
                fetchMovieCreditsUseCase(id)
            }
            val accountStatesDeferredResult = async {
                fetchMoviesAccountStatesUseCase(id)
            }

            val movieDetailsResult: Result<MovieDetails> = movieDetailsDeferredResult.await()
            val creditsResult: Result<Credits> = creditDeferredResult.await()
            val accountStatesResult: Result<AccountStates> = accountStatesDeferredResult.await()

            val result: Result<MovieDetailsScreenState> = flatZip(
                movieDetailsResult,
                creditsResult,
                accountStatesResult
            ) { movieDetails, credits, accountStates ->
                Result.Success(
                    MovieDetailsScreenState(
                        movieDetails = movieDetails,
                        credits = credits,
                        favorite = accountStates.favorite
                    )
                )
            }

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

    fun onFavoriteErrorHandled() {
        _state.value = _state.value.copy(favoriteHasError = false)
    }
}

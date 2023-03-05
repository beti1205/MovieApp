/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.MediaType
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.accountstates.domain.FetchTVAccountStatesUseCase
import com.beti1205.movieapp.feature.credits.data.Credits
import com.beti1205.movieapp.feature.credits.domain.FetchTVSeriesCreditsUseCase
import com.beti1205.movieapp.feature.favorite.domain.MarkFavoriteUseCase
import com.beti1205.movieapp.feature.tvepisodes.data.Episode
import com.beti1205.movieapp.feature.tvepisodes.domain.FetchEpisodesUseCase
import com.beti1205.movieapp.feature.tvseriesdetails.data.Season
import com.beti1205.movieapp.feature.tvseriesdetails.data.TVSeriesDetails
import com.beti1205.movieapp.feature.tvseriesdetails.domain.FetchTVSeriesDetailsUseCase
import com.beti1205.movieapp.feature.watchlist.domain.AddToWatchlistUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVSeriesDetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val fetchTVSeriesDetailsUseCase: FetchTVSeriesDetailsUseCase,
    private val fetchEpisodesUseCase: FetchEpisodesUseCase,
    private val fetchTVSeriesCreditsUseCase: FetchTVSeriesCreditsUseCase,
    private val fetchTVAccountStatesUseCase: FetchTVAccountStatesUseCase,
    private val markFavoriteUseCase: MarkFavoriteUseCase,
    private val addToWatchlistUseCase: AddToWatchlistUseCase,
    private val authManager: AuthManager
) : ViewModel() {

    private val tvSeriesDetailsArgs = TVSeriesDetailsArgs(state)

    private val _tvSeriesDetails = MutableStateFlow<TVSeriesDetails?>(null)
    val tvSeriesDetails: StateFlow<TVSeriesDetails?> = _tvSeriesDetails.asStateFlow()

    private val _selectedSeason = MutableStateFlow<Season?>(null)
    val selectedSeason: StateFlow<Season?> = _selectedSeason.asStateFlow()

    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError.asStateFlow()

    private val _favoriteHasError = MutableStateFlow(false)
    val favoriteHasError: StateFlow<Boolean> = _favoriteHasError.asStateFlow()

    private val _credits = MutableStateFlow<Credits?>(null)
    val credits: StateFlow<Credits?> = _credits.asStateFlow()

    private val _isFavorite = MutableStateFlow(false)
    val isFavorite: StateFlow<Boolean> = _isFavorite.asStateFlow()

    private val _isAddedToWatchlist = MutableStateFlow(false)
    val isAddedToWatchlist: StateFlow<Boolean> = _isAddedToWatchlist.asStateFlow()

    private val _watchlistError = MutableStateFlow(false)
    val watchlistError: StateFlow<Boolean> = _watchlistError.asStateFlow()

    val isLoggedIn = authManager.isLoggedInFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        false
    )

    val episodes: StateFlow<List<Episode>> =
        selectedSeason.map { season ->
            if (season == null) {
                return@map emptyList()
            }

            when (
                val result = fetchEpisodesUseCase(
                    tvSeriesDetailsArgs.selectedTVSeriesId,
                    season.seasonNumber
                )
            ) {
                is Result.Error -> emptyList()
                is Result.Success -> result.data.episodes
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        tvSeriesDetailsArgs.selectedTVSeriesId.apply {
            fetchTVSeriesDetails(this)
            fetchTVSeriesCredits(this)
            fetchTVAccountStates(this)
        }
    }

    private fun fetchTVSeriesDetails(id: Int) {
        viewModelScope.launch {
            val result = fetchTVSeriesDetailsUseCase(id)

            when (result) {
                is Result.Success -> {
                    _tvSeriesDetails.value = result.data
                    _selectedSeason.value = result.data.seasons.first()
                }
                is Result.Error -> _hasError.value = true
            }
        }
    }

    private fun fetchTVSeriesCredits(id: Int) {
        viewModelScope.launch {
            val result = fetchTVSeriesCreditsUseCase(id)

            when (result) {
                is Result.Success -> _credits.value = result.data
                is Result.Error -> {}
            }
        }
    }

    private fun fetchTVAccountStates(id: Int) {
        viewModelScope.launch {
            val result = fetchTVAccountStatesUseCase(id)

            when (result) {
                is Result.Success -> _isFavorite.value = result.data.favorite
                is Result.Error -> {}
            }
        }
    }

    fun markFavorite(favorite: Boolean) {
        viewModelScope.launch {
            if (!authManager.isLoggedIn) {
                return@launch
            }

            _isFavorite.value = favorite

            val result = markFavoriteUseCase(
                favorite = favorite,
                mediaType = MediaType.TV,
                mediaId = tvSeriesDetailsArgs.selectedTVSeriesId
            )

            if (result is Result.Error) {
                _isFavorite.value = !favorite
                _favoriteHasError.value = true
            }
        }
    }

    fun addToWatchlist(watchlist: Boolean) {
        viewModelScope.launch {
            if (!authManager.isLoggedIn) {
                return@launch
            }

            _isAddedToWatchlist.value = watchlist

            val result = addToWatchlistUseCase(
                watchlist = watchlist,
                mediaType = MediaType.TV,
                mediaId = tvSeriesDetailsArgs.selectedTVSeriesId
            )

            if (result is Result.Error) {
                _isAddedToWatchlist.value = !watchlist
                _watchlistError.value = true
            }
        }
    }

    fun onFavoriteErrorHandled() {
        _favoriteHasError.value = false
    }

    fun onWatchlistErrorHandled() {
        _watchlistError.value = false
    }

    fun setSelectedSeason(season: Season) {
        _selectedSeason.value = season
    }
}

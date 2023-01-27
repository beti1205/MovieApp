package com.beti1205.movieapp.ui.tvseries.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.accountstates.domain.FetchTVAccountStatesUseCase
import com.beti1205.movieapp.feature.credits.data.Credits
import com.beti1205.movieapp.feature.credits.domain.FetchTVSeriesCreditsUseCase
import com.beti1205.movieapp.feature.favorite.domain.MarkFavoriteUseCase
import com.beti1205.movieapp.feature.favorite.domain.MediaType
import com.beti1205.movieapp.feature.tvepisodes.data.Episode
import com.beti1205.movieapp.feature.tvepisodes.domain.FetchEpisodesUseCase
import com.beti1205.movieapp.feature.tvseriesdetails.data.Season
import com.beti1205.movieapp.feature.tvseriesdetails.data.TVSeriesDetails
import com.beti1205.movieapp.feature.tvseriesdetails.domain.FetchTVSeriesDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
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
    private val authManager: AuthManager
) : ViewModel() {

    val selectedTVSeriesId = state.getStateFlow(
        "selectedTVSeriesId",
        -1
    )

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

    private val _favorite = MutableStateFlow(false)
    val favorite: StateFlow<Boolean> = _favorite.asStateFlow()

    val isLoggedIn = authManager.isLoggedInFlow.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        false
    )

    val episodes: StateFlow<List<Episode>> = selectedTVSeriesId
        .combine(selectedSeason) { tvSeriesId, season ->
            if (season == null) {
                return@combine emptyList()
            }

            when (
                val result = fetchEpisodesUseCase(
                    tvSeriesId,
                    season.seasonNumber
                )
            ) {
                is Result.Error -> emptyList()
                is Result.Success -> result.data.episodes
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        val selectedTvSeriesId = selectedTVSeriesId.value
        fetchTVSeriesDetails(selectedTvSeriesId)
        fetchTVSeriesCredits(selectedTvSeriesId)
        fetchTVAccountStates(selectedTvSeriesId)
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
                is Result.Success -> _favorite.value = result.data.favorite
                is Result.Error -> {}
            }
        }
    }

    fun markFavorite(favorite: Boolean) {
        viewModelScope.launch {
            if (!authManager.isLoggedIn) {
                return@launch
            }

            _favorite.value = favorite

            val result = markFavoriteUseCase(
                favorite = favorite,
                mediaType = MediaType.TV,
                mediaId = selectedTVSeriesId.value
            )

            if (result is Result.Error) {
                _favorite.value = !favorite
                _favoriteHasError.value = true
            }
        }
    }

    fun onFavoriteErrorHandled() {
        _favoriteHasError.value = false
    }

    fun setSelectedSeason(season: Season) {
        _selectedSeason.value = season
    }
}

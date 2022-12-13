package com.beti1205.movieapp.ui.tvseries.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.feature.fetchtvepisodes.domain.FetchEpisodesUseCase
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Season
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.TVSeriesDetails
import com.beti1205.movieapp.feature.fetchtvseriesdetails.domain.FetchTVSeriesDetailsUseCase
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
    private val fetchEpisodesUseCase: FetchEpisodesUseCase
) : ViewModel() {

    val selectedTVSeriesId = state.getStateFlow<Int?>(
        "selectedTVSeriesId",
        null
    )

    private val _tvSeriesDetails = MutableStateFlow<TVSeriesDetails?>(null)
    val tvSeriesDetails: StateFlow<TVSeriesDetails?> = _tvSeriesDetails.asStateFlow()

    private val _selectedSeason = MutableStateFlow<Season?>(null)
    val selectedSeason: StateFlow<Season?> = _selectedSeason.asStateFlow()

    private val _hasError = MutableStateFlow<Boolean>(false)
    val hasError: StateFlow<Boolean> = _hasError.asStateFlow()

    val episodes: StateFlow<List<Episode>> = selectedTVSeriesId
        .combine(selectedSeason) { tvSeriesId, season ->
            if (season == null || tvSeriesId == null) {
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
        if (selectedTvSeriesId != null) {
            fetchTVSeriesDetails(selectedTvSeriesId)
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

    fun setSelectedSeason(season: Season) {
        _selectedSeason.value = season
    }
}

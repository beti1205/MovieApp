package com.beti1205.movieapp.ui.tvseries.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.feature.fetchtvepisodes.domain.FetchEpisodesUseCase
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Season
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

    val selectedTVSeries = state.getStateFlow<TVSeries?>(
        "selectedTVSeries",
        null
    )

    private val _seasons = MutableStateFlow<List<Season>>(emptyList())
    val seasons: StateFlow<List<Season>> = _seasons.asStateFlow()

    private val _genres = MutableStateFlow<List<Genre>>(emptyList())
    val genres: StateFlow<List<Genre>> = _genres.asStateFlow()

    private val _selectedSeason = MutableStateFlow<Season?>(null)
    val selectedSeason: StateFlow<Season?> = _selectedSeason.asStateFlow()

    private val _hasError = MutableStateFlow<Boolean>(false)
    val hasError: StateFlow<Boolean> = _hasError.asStateFlow()

    val episodes: StateFlow<List<Episode>> = selectedTVSeries
        .combine(selectedSeason) { tvSeries, season ->
            if (season == null || tvSeries == null) {
                return@combine emptyList()
            }

            when (
                val result = fetchEpisodesUseCase(
                    tvSeries.id,
                    season.seasonNumber
                )
            ) {
                is Result.Error -> emptyList()
                is Result.Success -> result.data.episodes
            }
        }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    init {
        val selectedTvSeriesId = selectedTVSeries.value?.id
        if (selectedTvSeriesId != null) {
            fetchSeasons(selectedTvSeriesId)
        }
    }

    private fun fetchSeasons(id: Int) {
        viewModelScope.launch {
            val result = fetchTVSeriesDetailsUseCase(id)

            when (result) {
                is Result.Success -> {
                    _seasons.value = result.data.seasons
                    _genres.value = result.data.genres
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

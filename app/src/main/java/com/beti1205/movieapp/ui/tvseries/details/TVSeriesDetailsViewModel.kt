package com.beti1205.movieapp.ui.tvseries.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asFlow
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.feature.fetchtvepisodes.domain.FetchEpisodesUseCase
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Genre
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Season
import com.beti1205.movieapp.feature.fetchtvseriesdetails.domain.FetchTVSeriesDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
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

    private val _selectedTVSeries = state.getLiveData<TVSeries>(
        "selectedTVSeries"
    )
    val selectedTVSeries: LiveData<TVSeries> = _selectedTVSeries

    private val _seasons = MutableLiveData<List<Season>>()
    val seasons: LiveData<List<Season>> = _seasons

    private val _genres = MutableLiveData<List<Genre>>()
    val genres: LiveData<List<Genre>> = _genres

    private val _hasError = MutableLiveData<Boolean>(false)
    val hasError: LiveData<Boolean> = _hasError

    private val selectedSeasonPosition = MutableLiveData<Int>()

    val selectedSeason: LiveData<Season?> = selectedSeasonPosition
        .map { position -> seasons.value?.get(position) }

    val episodes: StateFlow<List<Episode>> = selectedTVSeries.asFlow()
        .combine(selectedSeason.asFlow()) { tvSeries, season ->
            if (season == null) {
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
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun fetchSeasons(id: Int) {
        viewModelScope.launch {
            val result = fetchTVSeriesDetailsUseCase(id)

            when (result) {
                is Result.Success -> {
                    _seasons.value = result.data.seasons
                    _genres.value = result.data.genres
                    selectedSeasonPosition.value = if (result.data.seasons.size > 1) {
                        1
                    } else {
                        0
                    }
                }
                is Result.Error -> _hasError.value = true
            }
        }
    }

    fun setSelectedSeasonPosition(position: Int) {
        selectedSeasonPosition.value = position
    }
}

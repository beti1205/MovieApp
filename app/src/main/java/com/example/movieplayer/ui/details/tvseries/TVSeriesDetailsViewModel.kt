package com.example.movieplayer.ui.details.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import androidx.lifecycle.viewModelScope
import com.example.movieplayer.common.Result
import com.example.movieplayer.feature.fetchtvseries.data.TVSeries
import com.example.movieplayer.feature.fetchtvseriesdetails.data.Season
import com.example.movieplayer.feature.fetchtvseriesdetails.domain.FetchTVSeriesDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVSeriesDetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val fetchTVSeriesDetailsUseCase: FetchTVSeriesDetailsUseCase
) : ViewModel() {

    private val _selectedTVSeries = state.getLiveData<TVSeries>("selectedTVSeries")
    val selectedTVSeries: LiveData<TVSeries> = _selectedTVSeries

    private val _seasons = MutableLiveData<List<Season>>()
    val seasons: LiveData<List<Season>> = _seasons

    private val selectedSeasonPosition = MutableLiveData<Int>()

    val selectedSeason: LiveData<Season?> = selectedSeasonPosition
        .map { position -> seasons.value?.get(position) }

    fun fetchSeasons(id: Int) {
        viewModelScope.launch {
            val result = fetchTVSeriesDetailsUseCase(id)

            when (result) {
                is Result.Success -> {
                    _seasons.value = result.data.seasons
                    if (result.data.seasons.size == 1) {
                        selectedSeasonPosition.value = 0
                    } else {
                        selectedSeasonPosition.value = 1
                    }
                }
                is Result.Error -> result
            }
        }
    }

    fun setSelectedSeasonPosition(position: Int) {
        selectedSeasonPosition.value = position
    }
}

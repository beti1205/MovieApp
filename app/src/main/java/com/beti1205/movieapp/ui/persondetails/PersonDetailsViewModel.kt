/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.persondetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.persondetails.data.PersonDetails
import com.beti1205.movieapp.feature.persondetails.domain.FetchPersonDetailsUseCase
import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCreditsResponse
import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.feature.personmoviecredits.domain.FetchPersonMovieCreditsUseCase
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCast
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCreditsResponse
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCrew
import com.beti1205.movieapp.feature.persontvseriescredits.domain.FetchPersonTVSeriesCreditsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val fetchPersonDetailsUseCase: FetchPersonDetailsUseCase,
    private val fetchPersonMovieCreditsUseCase: FetchPersonMovieCreditsUseCase,
    private val fetchPersonTVSeriesCreditsUseCase: FetchPersonTVSeriesCreditsUseCase
) : ViewModel() {

    private val selectedPersonId = state.getStateFlow<Int>("selectedPersonId", -1)

    private val _personDetails = MutableStateFlow<PersonDetails?>(null)
    val personDetails: StateFlow<PersonDetails?> = _personDetails.asStateFlow()

    private val _isLoading = MutableStateFlow<Boolean>(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    private val _hasError = MutableStateFlow<Boolean>(false)
    val hasError: StateFlow<Boolean> = _hasError.asStateFlow()

    private val _hasCreditsError = MutableStateFlow<Boolean>(false)
    val hasCreditsError: StateFlow<Boolean> = _hasCreditsError.asStateFlow()

    private val _sectionsStatuses: MutableStateFlow<Map<SectionType, Boolean>> = MutableStateFlow(
        mapOf(
            SectionType.MOVIE_CAST to false,
            SectionType.TV_CAST to false,
            SectionType.MOVIE_CREW to false,
            SectionType.TV_CREW to false
        )
    )
    val sectionStatuses: StateFlow<Map<SectionType, Boolean>> = _sectionsStatuses.asStateFlow()

    private val _personMovieCast = MutableStateFlow<List<PersonMovieCast>>(emptyList())
    val movieCastSection = combine(_personMovieCast, _sectionsStatuses) { movieCast, statuses ->
        val expanded = statuses[SectionType.MOVIE_CAST] == true

        Section(
            items = if (expanded) movieCast else movieCast.take(5),
            expanded = expanded,
            expandable = movieCast.size > 5
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), Section())

    private val _personMovieCrew = MutableStateFlow<List<PersonMovieCrew>>(emptyList())
    val movieCrewSection = combine(_personMovieCrew, _sectionsStatuses) { movieCrew, statuses ->
        val expanded = statuses[SectionType.MOVIE_CREW] == true

        Section(
            items = if (expanded) movieCrew else movieCrew.take(5),
            expanded = expanded,
            expandable = movieCrew.size > 5
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), Section())

    private val _personTVSeriesCast = MutableStateFlow<List<PersonTVSeriesCast>>(emptyList())
    val tvCastSection = combine(_personTVSeriesCast, _sectionsStatuses) { tvCast, statuses ->
        val expanded = statuses[SectionType.TV_CAST] == true

        Section(
            items = if (expanded) tvCast else tvCast.take(5),
            expanded = expanded,
            expandable = tvCast.size > 5
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), Section())

    private val _personTVSeriesCrew = MutableStateFlow<List<PersonTVSeriesCrew>>(emptyList())
    val tvCrewSection = combine(_personTVSeriesCrew, _sectionsStatuses) { tvCrew, statuses ->
        val expanded = statuses[SectionType.TV_CREW] == true

        Section(
            items = if (expanded) tvCrew else tvCrew.take(5),
            expanded = expanded,
            expandable = tvCrew.size > 5
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), Section())

    init {
        val selectedPersonId = selectedPersonId.value
        fetchPersonDetails(selectedPersonId)
        fetchPersonSectionsItems(selectedPersonId)
    }

    private fun fetchPersonDetails(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true

            val result = fetchPersonDetailsUseCase(id)

            when (result) {
                is Result.Success -> {
                    _personDetails.value = result.data
                    _isLoading.value = false
                }
                is Result.Error -> {
                    _isLoading.value = false
                    _hasError.value = true
                }
            }
        }
    }

    private fun fetchPersonSectionsItems(personId: Int) {
        viewModelScope.launch {
            val movieDeferredResult = async {
                fetchPersonMovieCreditsUseCase(personId)
            }
            val tvDeferredResult = async {
                fetchPersonTVSeriesCreditsUseCase(personId)
            }

            val movieResult: Result<PersonMovieCreditsResponse> = movieDeferredResult.await()
            val tvResult: Result<PersonTVSeriesCreditsResponse> = tvDeferredResult.await()

            if (movieResult is Result.Success && tvResult is Result.Success) {
                _personMovieCast.value = movieResult.data.cast
                _personMovieCrew.value = movieResult.data.crew

                _personTVSeriesCast.value = tvResult.data.cast
                _personTVSeriesCrew.value = tvResult.data.crew

                return@launch
            }

            _hasCreditsError.value = true
        }
    }

    fun onSectionExpandedChanged(sectionType: SectionType, expanded: Boolean) {
        _sectionsStatuses.value = _sectionsStatuses.value + (sectionType to expanded)
    }
}

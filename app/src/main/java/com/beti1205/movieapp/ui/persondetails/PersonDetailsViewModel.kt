/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.persondetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.personcredits.domain.FetchPersonCreditsUseCase
import com.beti1205.movieapp.feature.persondetails.data.PersonDetails
import com.beti1205.movieapp.feature.persondetails.domain.FetchPersonDetailsUseCase
import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCast
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCrew
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val fetchPersonDetailsUseCase: FetchPersonDetailsUseCase,
    private val fetchPersonCreditsUseCase: FetchPersonCreditsUseCase
) : ViewModel() {

    private val personDetailsArgs = PersonDetailsArgs(state)

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
    val movieCastSection = combine(_personMovieCast, _sectionsStatuses) { items, statuses ->
        processSection(
            sectionType = SectionType.MOVIE_CAST,
            items = items,
            statuses = statuses
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), Section())

    private val _personMovieCrew = MutableStateFlow<List<PersonMovieCrew>>(emptyList())
    val movieCrewSection = combine(_personMovieCrew, _sectionsStatuses) { items, statuses ->
        processSection(
            sectionType = SectionType.MOVIE_CREW,
            items = items,
            statuses = statuses
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), Section())

    private val _personTVSeriesCast = MutableStateFlow<List<PersonTVSeriesCast>>(emptyList())
    val tvCastSection = combine(_personTVSeriesCast, _sectionsStatuses) { items, statuses ->
        processSection(
            sectionType = SectionType.TV_CAST,
            items = items,
            statuses = statuses
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), Section())

    private val _personTVSeriesCrew = MutableStateFlow<List<PersonTVSeriesCrew>>(emptyList())
    val tvCrewSection = combine(_personTVSeriesCrew, _sectionsStatuses) { items, statuses ->
        processSection(
            sectionType = SectionType.TV_CREW,
            items = items,
            statuses = statuses
        )
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), Section())

    init {
        val selectedPersonId = personDetailsArgs.selectedPersonId
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
            val result = fetchPersonCreditsUseCase(personId)

            if (result is Result.Success) {
                _personMovieCast.value = result.data.personMovieCast
                _personMovieCrew.value = result.data.personMovieCrew

                _personTVSeriesCast.value = result.data.personTVSeriesCast
                _personTVSeriesCrew.value = result.data.personTVSeriesCrew

                return@launch
            }

            _hasCreditsError.value = true
        }
    }

    fun onSectionExpandedChanged(sectionType: SectionType, expanded: Boolean) {
        _sectionsStatuses.value = _sectionsStatuses.value + (sectionType to expanded)
    }

    private fun <T> processSection(
        sectionType: SectionType,
        items: List<T>,
        statuses: Map<SectionType, Boolean>
    ): Section<T> {
        val expanded = statuses[sectionType] == true

        return Section(
            items = if (expanded) items else items.take(5),
            expanded = expanded,
            expandable = items.size > 5
        )
    }
}

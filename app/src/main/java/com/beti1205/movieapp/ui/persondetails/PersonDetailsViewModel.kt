package com.beti1205.movieapp.ui.persondetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.feature.fetchpersondetails.domain.FetchPersonDetailsUseCase
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.domain.FetchPersonMovieCreditsUseCase
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCast
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCrew
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.domain.FetchPersonTVSeriesCreditsUseCase
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
    private val _personMovieCrew = MutableStateFlow<List<PersonMovieCrew>>(emptyList())
    private val _personTVSeriesCast = MutableStateFlow<List<PersonTVSeriesCast>>(emptyList())
    private val _personTVSeriesCrew = MutableStateFlow<List<PersonTVSeriesCrew>>(emptyList())

    val sections: StateFlow<List<Section>> = combine(
        _personMovieCast,
        _personMovieCrew,
        _personTVSeriesCast,
        _personTVSeriesCrew,
        _sectionsStatuses
    ) { movieCast, movieCrew, tvCast, tvCrew, status ->
        createSections(status, movieCast, movieCrew, tvCast, tvCrew)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000L), emptyList())

    init {
        val selectedPersonId = selectedPersonId.value
        fetchPersonDetails(selectedPersonId)
        fetchPersonMovieCredits(selectedPersonId)
        fetchPersonTVSeriesCredits(selectedPersonId)
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

    private fun fetchPersonMovieCredits(personId: Int) {
        viewModelScope.launch {
            val result = fetchPersonMovieCreditsUseCase(personId)

            when (result) {
                is Result.Success -> {
                    _personMovieCast.value = result.data.cast
                    _personMovieCrew.value = result.data.crew
                }
                is Result.Error -> _hasCreditsError.value = true
            }
        }
    }

    private fun fetchPersonTVSeriesCredits(personId: Int) {
        viewModelScope.launch {
            val result = fetchPersonTVSeriesCreditsUseCase(personId)

            when (result) {
                is Result.Success -> {
                    _personTVSeriesCast.value = result.data.cast
                    _personTVSeriesCrew.value = result.data.crew
                }
                is Result.Error -> _hasCreditsError.value = true
            }
        }
    }

    fun onSectionExpandedChanged(section: Section, expanded: Boolean) {
        val sectionType = when (section) {
            is Section.MovieCast -> SectionType.MOVIE_CAST
            is Section.TVCast -> SectionType.TV_CAST
            is Section.MovieCrew -> SectionType.MOVIE_CREW
            is Section.TVCrew -> SectionType.TV_CREW
        }
        _sectionsStatuses.value = _sectionsStatuses.value + (sectionType to expanded)
    }

    private fun createSections(
        statuses: Map<SectionType, Boolean>,
        movieCast: List<PersonMovieCast>,
        movieCrew: List<PersonMovieCrew>,
        tvCast: List<PersonTVSeriesCast>,
        tvCrew: List<PersonTVSeriesCrew>
    ): List<Section> {
        val movieCastExpanded = statuses[SectionType.MOVIE_CAST] == true
        val movieCastItems = if (movieCastExpanded) movieCast else movieCast.take(5)
        val movieCrewExpanded = statuses[SectionType.MOVIE_CREW] == true
        val movieCrewItems = if (movieCrewExpanded) movieCrew else movieCrew.take(5)
        val tvCastExpanded = statuses[SectionType.TV_CAST] == true
        val tvCastItems = if (tvCastExpanded) tvCast else tvCast.take(5)
        val tvCrewExpanded = statuses[SectionType.TV_CREW] == true
        val tvCrewItems = if (tvCrewExpanded) tvCrew else tvCrew.take(5)

        return listOf(
            Section.MovieCast(movieCastItems, movieCastExpanded),
            Section.TVCast(tvCastItems, tvCastExpanded),
            Section.MovieCrew(movieCrewItems, movieCrewExpanded),
            Section.TVCrew(tvCrewItems, tvCrewExpanded)
        )
    }
}

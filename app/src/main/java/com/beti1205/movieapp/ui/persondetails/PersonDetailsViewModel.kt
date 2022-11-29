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
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val fetchPersonDetailsUseCase: FetchPersonDetailsUseCase,
    private val fetchPersonMovieCreditsUseCase: FetchPersonMovieCreditsUseCase
) : ViewModel() {

    private val selectedPersonId = state.getStateFlow<Int>("selectedPersonId", -1)

    private val _personDetails = MutableStateFlow<PersonDetails?>(null)
    val personDetails: StateFlow<PersonDetails?> = _personDetails.asStateFlow()

    private val _personMovieCast = MutableStateFlow<List<PersonMovieCast>>(emptyList())
    val personMovieCast: StateFlow<List<PersonMovieCast>> = _personMovieCast.asStateFlow()

    private val _personMovieCrew = MutableStateFlow<List<PersonMovieCrew>>(emptyList())
    val personMovieCrew: StateFlow<List<PersonMovieCrew>> = _personMovieCrew.asStateFlow()

    init {
        val selectedPersonId = selectedPersonId.value
        fetchPersonDetails(selectedPersonId)
        fetchPersonMovieCredits(selectedPersonId)
    }

    private fun fetchPersonDetails(id: Int) {
        viewModelScope.launch {
            val result = fetchPersonDetailsUseCase(id)

            when (result) {
                is Result.Success -> _personDetails.value = result.data
                is Result.Error -> result
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
                is Result.Error -> result
            }
        }
    }
}

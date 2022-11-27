package com.beti1205.movieapp.ui.persondetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.feature.fetchpersondetails.domain.FetchPersonDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PersonDetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val fetchPersonDetailsUseCase: FetchPersonDetailsUseCase
) : ViewModel() {

    private val selectedPersonId = state.getStateFlow<Int>("selectedPersonId", -1)

    private val _personDetails = MutableStateFlow<PersonDetails?>(null)
    val personDetails: StateFlow<PersonDetails?> = _personDetails.asStateFlow()

    init {
        val selectedPersonId = selectedPersonId.value
        fetchPersonDetails(selectedPersonId)
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
}

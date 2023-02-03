package com.beti1205.movieapp.ui.tvseries.reviews

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.reviews.data.Review
import com.beti1205.movieapp.feature.reviews.domain.FetchTVSeriesReviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TVSeriesReviewsViewModel @Inject constructor(
    private val fetchTVSeriesReviewsUseCase: FetchTVSeriesReviewsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val tvSeriesId = savedStateHandle.getStateFlow<Int?>("tvSeriesId", null)

    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews.asStateFlow()

    private val _reviewsError = MutableStateFlow(false)
    val reviewsError: StateFlow<Boolean> = _reviewsError.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        val tvSeriesId = tvSeriesId.value
        if (tvSeriesId != null) {
            fetchTVSeriesReviews(tvSeriesId)
        }
    }

    private fun fetchTVSeriesReviews(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = fetchTVSeriesReviewsUseCase(id)

            when (result) {
                is Result.Success -> {
                    _reviews.value = result.data.results
                    _isLoading.value = false
                }
                is Result.Error -> {
                    _reviewsError.value = true
                    _isLoading.value = false
                }
            }
        }
    }
}

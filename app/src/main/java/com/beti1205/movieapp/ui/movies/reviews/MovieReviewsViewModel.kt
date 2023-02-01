package com.beti1205.movieapp.ui.movies.reviews

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.reviews.data.Review
import com.beti1205.movieapp.feature.reviews.domain.FetchMovieReviewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieReviewsViewModel @Inject constructor(
    private val fetchMovieReviewsUseCase: FetchMovieReviewsUseCase,
    savedStateHandle: SavedStateHandle
) : ViewModel() {
    private val movieId = savedStateHandle.getStateFlow<Int?>("movieId", null)

    private val _reviews = MutableStateFlow<List<Review>>(emptyList())
    val reviews: StateFlow<List<Review>> = _reviews.asStateFlow()

    private val _reviewsError = MutableStateFlow(false)
    val reviewsError: StateFlow<Boolean> = _reviewsError.asStateFlow()

    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()

    init {
        val movieId = movieId.value
        if (movieId != null) {
            fetchMovieReview(movieId)
        }
    }

    private fun fetchMovieReview(id: Int) {
        viewModelScope.launch {
            _isLoading.value = true
            val result = fetchMovieReviewsUseCase(id)

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

package com.beti1205.movieapp.ui.movies.reviews

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchmoviereviews.data.MovieReview
import com.beti1205.movieapp.feature.fetchmoviereviews.domain.FetchMovieReviewsUseCase
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
    private val movieId = savedStateHandle.getStateFlow("movieId", -1)

    private val _reviews = MutableStateFlow<List<MovieReview>>(emptyList())
    val reviews: StateFlow<List<MovieReview>?> = _reviews.asStateFlow()

    init {
        getMovieReview(movieId.value)
    }

    private fun getMovieReview(id: Int) {
        viewModelScope.launch {
            val result = fetchMovieReviewsUseCase(id)

            when (result) {
                is Result.Success -> _reviews.value = result.data.results
                is Result.Error -> {}
            }
        }
    }
}

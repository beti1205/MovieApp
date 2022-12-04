package com.beti1205.movieapp.ui.movies.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.flatZip
import com.beti1205.movieapp.feature.fetchcredits.data.Credits
import com.beti1205.movieapp.feature.fetchcredits.domain.FetchMovieCreditsUseCase
import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetails
import com.beti1205.movieapp.feature.fetchmoviedetails.domain.FetchMovieDetailsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val fetchMovieCreditsUseCase: FetchMovieCreditsUseCase,
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase
) : ViewModel() {

    val selectedMovieId = state.getStateFlow<Int?>("selectedMovieId", null)

    private val _state = MutableStateFlow(MovieDetailsScreenState())
    val state: StateFlow<MovieDetailsScreenState> = _state.asStateFlow()

    init {
        val selectedMovieId = selectedMovieId.value

        if (selectedMovieId != null) {
            fetchMovieDetails(selectedMovieId)
        }
    }

    private fun fetchMovieDetails(id: Int) {
        viewModelScope.launch {
            val movieDetailsDeferredResult = async {
                fetchMovieDetailsUseCase(id)
            }
            val creditDeferredResult = async {
                fetchMovieCreditsUseCase(id)
            }

            val movieDetailsResult: Result<MovieDetails> = movieDetailsDeferredResult.await()
            val creditsResult: Result<Credits> = creditDeferredResult.await()
            val result: Result<MovieDetailsScreenState> = flatZip(
                movieDetailsResult,
                creditsResult
            ) { movieDetails, credits ->
                Result.Success(
                    MovieDetailsScreenState(
                        movieDetails,
                        credits
                    )
                )
            }

            when (result) {
                is Result.Error -> _state.value = MovieDetailsScreenState(hasError = true)
                is Result.Success -> _state.value = result.data
            }
        }
    }
}

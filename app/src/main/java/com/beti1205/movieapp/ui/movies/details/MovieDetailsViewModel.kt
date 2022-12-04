package com.beti1205.movieapp.ui.movies.details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.flatZip
import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.feature.fetchcredits.data.Credits
import com.beti1205.movieapp.feature.fetchcredits.data.Crew
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

    private val _cast = MutableStateFlow<List<Cast>>(emptyList())
    val cast: StateFlow<List<Cast>> = _cast.asStateFlow()

    private val _crew = MutableStateFlow<List<Crew>>(emptyList())
    val crew: StateFlow<List<Crew>> = _crew.asStateFlow()

    private val _movieDetails = MutableStateFlow<MovieDetails?>(null)
    val movieDetails: StateFlow<MovieDetails?> = _movieDetails.asStateFlow()

    private val _hasError = MutableStateFlow<Boolean>(false)
    val hasError: StateFlow<Boolean> = _hasError.asStateFlow()

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
            val result: Result<Details> = flatZip(
                movieDetailsResult,
                creditsResult
            ) { movieDetails, credits ->
                Result.Success(
                    Details(
                        movieDetails,
                        credits
                    )
                )
            }

            when (result) {
                is Result.Error -> _hasError.value = true
                is Result.Success -> {
                    val data = result.data
                    _cast.value = data.credits.cast
                    _crew.value = data.credits.crew
                    _movieDetails.value = data.movieDetails
                    _hasError.value = false
                }
            }
        }
    }
}

private data class Details(
    val movieDetails: MovieDetails,
    val credits: Credits
)

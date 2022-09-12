package com.example.movieplayer.ui.movies.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieplayer.common.Result
import com.example.movieplayer.feature.fetchcredits.data.Cast
import com.example.movieplayer.feature.fetchcredits.data.Crew
import com.example.movieplayer.feature.fetchcredits.domain.FetchMovieCreditsUseCase
import com.example.movieplayer.feature.fetchmovies.data.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    state: SavedStateHandle,
    private val fetchMovieCreditsUseCase: FetchMovieCreditsUseCase
) : ViewModel() {

    private val _selectedMovie = state.getLiveData<Movie>("selectedMovie")
    val selectedMovie: LiveData<Movie> = _selectedMovie

    private val _cast = MutableLiveData<List<Cast>>()
    val cast: LiveData<List<Cast>> = _cast

    private val _crew = MutableLiveData<List<Crew>>()
    val crew: LiveData<List<Crew>> = _crew

    private val _hasError = MutableLiveData<Boolean>(false)
    val hasError: LiveData<Boolean> = _hasError

    fun fetchCredits(id: Int) {
        viewModelScope.launch {
            val result = fetchMovieCreditsUseCase(id)

            when (result) {
                is Result.Success -> {
                    _cast.value = result.data.cast
                    _crew.value = result.data.crew
                    _hasError.value = false
                }
                is Result.Error -> _hasError.value = true
            }
        }
    }
}

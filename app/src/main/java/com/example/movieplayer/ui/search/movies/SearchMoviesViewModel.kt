package com.example.movieplayer.ui.search.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieplayer.domain.Movie
import com.example.movieplayer.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val repository: MovieRepository
    ) : ViewModel() {

    private var searchJob: Job? = null
    private val _searchedMovies: MutableLiveData<List<Movie>?> = MutableLiveData()
    val searchedMovies: LiveData<List<Movie>?> = _searchedMovies

//
//    fun displayDataFromRepository(keyword: String) {
//        viewModelScope.launch {
//
//        }
//    }

    fun onMovieNameChanged(movieName: String){
        searchJob?.cancel()

        if (movieName.length < 3) {
            return
        }

        searchJob = viewModelScope.launch {
            delay(500)
            try {
                _searchedMovies.value = repository.searchMovies(movieName)
            } catch (networkError: Exception) {
            }
        }
    }
}
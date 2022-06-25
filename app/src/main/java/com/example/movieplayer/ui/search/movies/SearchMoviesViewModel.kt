package com.example.movieplayer.ui.search.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieplayer.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val repository: MovieRepository
    ) : ViewModel() {

    fun displayDataFromRepository(keyword: String) {
        viewModelScope.launch {
            try {
                repository.searchMovies(keyword)
            } catch (networkError: Exception) {
            }
        }
    }
}
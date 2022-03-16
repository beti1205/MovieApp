package com.example.movieplayer.ui.search.movies

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.movieplayer.database.getDatabase
import com.example.movieplayer.repository.MovieRepository
import com.example.movieplayer.ui.movies.MovieViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception

class SearchMoviesViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieRepository = MovieRepository(getDatabase(application))


    fun displayDataFromRepository(keyword: String) {
        viewModelScope.launch {
            try {
                repository.searchMovies(keyword)
            } catch (networkError: Exception) {
            }
        }
    }


    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(SearchMoviesViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return SearchMoviesViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}
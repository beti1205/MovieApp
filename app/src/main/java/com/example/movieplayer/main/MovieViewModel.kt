package com.example.movieplayer.main

import android.app.Application
import androidx.lifecycle.*
import com.example.movieplayer.database.getDatabase
import com.example.movieplayer.network.Movie
import com.example.movieplayer.repository.MovieRepository
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.Exception


class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieRepository = MovieRepository(getDatabase(application))
    val movies = repository.movies

    private var _eventNetworkError = MutableLiveData<Boolean>(false)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    private var _isNetworkErrorShown = MutableLiveData<Boolean>(false)

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown


    init {
        refreshDataFromRepository()
    }


    private fun refreshDataFromRepository() = viewModelScope.launch {
        try {
            repository.refreshMovies()
            _eventNetworkError.value = false
            _isNetworkErrorShown.value = false
        } catch (networkError: IOException) {
            if (movies.value.isNullOrEmpty()) {
                _eventNetworkError.value = true
            }
        }
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }


    class Factory(private val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(MovieViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return MovieViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}

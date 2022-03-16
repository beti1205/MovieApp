package com.example.movieplayer.ui.movies

import android.app.Application
import androidx.lifecycle.*
import com.example.movieplayer.database.getDatabase
import com.example.movieplayer.domain.Movie
import com.example.movieplayer.domain.Order
import com.example.movieplayer.repository.MovieRepository
import kotlinx.coroutines.launch
import java.io.IOException


class MovieViewModel(application: Application) : AndroidViewModel(application) {

    private val repository: MovieRepository = MovieRepository(getDatabase(application))
    private val _eventNetworkError = MutableLiveData<Boolean>(false)
    private val _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    private val _navigateToSelectedMovie= MutableLiveData<SelectedMovie>()
    private var order: Order = Order.POPULAR

    data class SelectedMovie(val movie: Movie, val position: Int)

    val movies = repository.movies

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val navigateToSelectedMovie: LiveData<SelectedMovie>
        get() = _navigateToSelectedMovie


//    init {
//        refreshDataFromRepository()
//    }

    private fun refreshDataFromRepository() = viewModelScope.launch {
        try {
            repository.refreshMovies(order)
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

    fun onOrderChanged(order: Order) {
        this.order = order
        refreshDataFromRepository()
    }

    fun displayMovieDetails(movie: Movie, position: Int) {
        _navigateToSelectedMovie.value = SelectedMovie(movie, position)
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedMovie.value = null
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

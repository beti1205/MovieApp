package com.example.movieplayer.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.movieplayer.domain.Movie
import com.example.movieplayer.domain.Order
import com.example.movieplayer.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val repository: MovieRepository
) : ViewModel() {

    private val _eventNetworkError = MutableLiveData<Boolean>(false)
    private val _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    private val _navigateToSelectedMovie = MutableLiveData<SelectedMovie>()
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
}

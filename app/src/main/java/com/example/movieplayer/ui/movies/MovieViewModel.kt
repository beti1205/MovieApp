package com.example.movieplayer.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieplayer.feature.fetchmovies.data.MoviesPagingSource
import com.example.movieplayer.feature.fetchmovies.domain.FetchMoviesUseCase
import com.example.movieplayer.feature.fetchmovies.data.Movie
import com.example.movieplayer.feature.fetchmovies.domain.MovieOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val fetchMoviesUseCase: FetchMoviesUseCase
) : ViewModel() {

    private val _eventNetworkError = MutableLiveData<Boolean>(false)
    private val _isNetworkErrorShown = MutableLiveData<Boolean>(false)
    private val _navigateToSelectedMovie = MutableLiveData<SelectedMovie>()
    private val _order: MutableStateFlow<MovieOrder> = MutableStateFlow(MovieOrder.POPULAR)
    private val order = _order.asStateFlow()

    data class SelectedMovie(val movie: Movie, val position: Int)

    val eventNetworkError: LiveData<Boolean>
        get() = _eventNetworkError

    val isNetworkErrorShown: LiveData<Boolean>
        get() = _isNetworkErrorShown

    val navigateToSelectedMovie: LiveData<SelectedMovie>
        get() = _navigateToSelectedMovie

    @OptIn(ExperimentalCoroutinesApi::class)
    val flow: Flow<PagingData<Movie>> = order.flatMapLatest { order ->
        Pager(
            PagingConfig(pageSize = 20)
        ) {
           MoviesPagingSource(fetchMoviesUseCase, order)
        }
            .flow
            .cachedIn(viewModelScope)
    }

    fun onNetworkErrorShown() {
        _isNetworkErrorShown.value = true
    }

    fun onOrderChanged(order: MovieOrder) {
        _order.value = order
    }

    fun displayMovieDetails(movie: Movie, position: Int) {
        _navigateToSelectedMovie.value = SelectedMovie(movie, position)
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }
}

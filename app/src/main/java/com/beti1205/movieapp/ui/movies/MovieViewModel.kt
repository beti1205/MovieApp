package com.beti1205.movieapp.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.beti1205.movieapp.feature.fetchmovies.data.Movie
import com.beti1205.movieapp.feature.fetchmovies.domain.FetchMoviesUseCase
import com.beti1205.movieapp.feature.fetchmovies.domain.MovieOrder
import com.beti1205.movieapp.ui.common.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class MovieViewModel @Inject constructor(
    private val fetchMoviesUseCase: FetchMoviesUseCase,
    private val preferences: Preferences
) : ViewModel() {

    private val _order: MutableStateFlow<MovieOrder> = MutableStateFlow(MovieOrder.POPULAR)
    val order = _order.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val movies: Flow<PagingData<Movie>> = order.flatMapLatest { order ->
        Pager(
            PagingConfig(pageSize = 20)
        ) {
            MoviesPagingSource(fetchMoviesUseCase, order)
        }.flow
    }.cachedIn(viewModelScope)

    init {
        _order.value = MovieOrder.from(preferences.movieOrder)
    }

    fun onOrderChanged(order: MovieOrder) {
        _order.value = order
        preferences.movieOrder = order.ordinal
    }
}

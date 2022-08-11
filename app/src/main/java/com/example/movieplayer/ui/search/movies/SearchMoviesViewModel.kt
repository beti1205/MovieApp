package com.example.movieplayer.ui.search.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieplayer.feature.fetchmovies.data.SearchMoviesPagingSource
import com.example.movieplayer.feature.fetchmovies.data.Movie
import com.example.movieplayer.feature.fetchmovies.domain.SearchMoviesUseCase
import com.example.movieplayer.ui.movies.MovieViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase
) : ViewModel() {


    private val queryFlow = MutableStateFlow("")
    private val _navigateToSelectedMovie = MutableLiveData<MovieViewModel.SelectedMovie>()
    val navigateToSelectedMovie: LiveData<MovieViewModel.SelectedMovie>
        get() = _navigateToSelectedMovie


    @OptIn(ExperimentalCoroutinesApi::class)
    val querySearchResults = queryFlow
        .filter { query -> query.length > 3 }
        .debounce(300)
        .flatMapLatest { query ->
            getSearchResultStream(query)
        }.cachedIn(viewModelScope)

    fun onQueryChanged(query: String) {
        queryFlow.value = query
    }

    private fun getSearchResultStream(
        query: String
    ): Flow<PagingData<Movie>> {
        return Pager(
            PagingConfig(pageSize = 20)
        ) {
            SearchMoviesPagingSource(searchMoviesUseCase, query)
        }
            .flow
    }
    fun displayMovieDetails(movie: Movie, position: Int) {
        _navigateToSelectedMovie.value = MovieViewModel.SelectedMovie(movie, position)
    }

    fun displayPropertyDetailsComplete() {
        _navigateToSelectedMovie.value = null
    }
}
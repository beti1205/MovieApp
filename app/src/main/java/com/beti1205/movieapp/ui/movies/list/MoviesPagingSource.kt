package com.beti1205.movieapp.ui.movies.list

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.feature.movies.domain.FetchMoviesUseCase
import com.beti1205.movieapp.feature.movies.domain.MovieOrder
import com.beti1205.movieapp.ui.common.getNextPageKey
import com.beti1205.movieapp.ui.movies.common.getMovieRefreshKey

class MoviesPagingSource(
    val fetchMoviesUseCase: FetchMoviesUseCase,
    private val movieOrder: MovieOrder
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        val nextPageNumber = params.key ?: 1
        val response = fetchMoviesUseCase(movieOrder, nextPageNumber)
        return when (response) {
            is Result.Success -> {
                LoadResult.Page(
                    data = response.data.items,
                    prevKey = null,
                    nextKey = response.data.getNextPageKey()
                )
            }
            is Result.Error -> {
                Log.d("MoviesPagingSource", response.message.toString())
                LoadResult.Error(response.message ?: Exception("Unable to load items"))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Movie>): Int? {
        return getMovieRefreshKey(state)
    }
}

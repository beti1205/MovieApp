package com.beti1205.movieapp.ui.movies.search

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchmovies.data.Movie
import com.beti1205.movieapp.feature.fetchmovies.domain.SearchMoviesUseCase
import com.beti1205.movieapp.ui.common.TooShortQueryException
import com.beti1205.movieapp.ui.common.getNextPageKey
import com.beti1205.movieapp.ui.movies.getMovieRefreshKey

class SearchMoviesPagingSource(
    val searchMoviesUseCase: SearchMoviesUseCase,
    private val query: String
) : PagingSource<Int, Movie>() {

    companion object {
        const val MIN_QUERY_LENGTH = 3
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        if (query.length <= MIN_QUERY_LENGTH) {
            return LoadResult.Error(TooShortQueryException)
        }

        val nextPageNumber = params.key ?: 1
        val response = searchMoviesUseCase(query, nextPageNumber)
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

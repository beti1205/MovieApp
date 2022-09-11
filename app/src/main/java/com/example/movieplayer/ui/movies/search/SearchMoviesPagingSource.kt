package com.example.movieplayer.feature.fetchmovies.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieplayer.common.Result
import com.example.movieplayer.feature.fetchmovies.data.Movie
import com.example.movieplayer.feature.fetchmovies.domain.SearchMoviesUseCase
import com.example.movieplayer.ui.common.TooShortQueryException
import com.example.movieplayer.ui.common.getNextPageKey
import com.example.movieplayer.ui.movies.getMovieRefreshKey

class SearchMoviesPagingSource(
    val searchMoviesUseCase: SearchMoviesUseCase,
    private val query: String
) : PagingSource<Int, Movie>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Movie> {
        if (query.length <= 3) {
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

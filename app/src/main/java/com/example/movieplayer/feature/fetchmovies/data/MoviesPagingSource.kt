package com.example.movieplayer.feature.fetchmovies.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieplayer.common.Result
import com.example.movieplayer.common.getNextPageKey
import com.example.movieplayer.feature.fetchmovies.domain.FetchMoviesUseCase
import com.example.movieplayer.feature.fetchmovies.domain.MovieOrder

class MoviesPagingSource (
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
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)

        }
    }
}
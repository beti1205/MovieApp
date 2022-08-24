package com.example.movieplayer.feature.fetchtvseries.data

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieplayer.common.Result
import com.example.movieplayer.common.getNextPageKey
import com.example.movieplayer.feature.fetchtvseries.domain.SearchTVSeriesUseCase

class SearchTVSeriesPagingSource(
    val searchTVSeriesUseCase: SearchTVSeriesUseCase,
    private val query: String
) : PagingSource<Int, TVSeries>() {

    override suspend fun load(params: PagingSource.LoadParams<Int>): PagingSource.LoadResult<Int, TVSeries> {
        val nextPageNumber = params.key ?: 1
        val response = searchTVSeriesUseCase(query, nextPageNumber)
        return when (response) {
            is Result.Success -> {
                PagingSource.LoadResult.Page(
                    data = response.data.items,
                    prevKey = null,
                    nextKey = response.data.getNextPageKey()
                )
            }
            is Result.Error -> {
                Log.d("SearchTVSeriesPagingSource", response.message.toString())
                PagingSource.LoadResult.Error(response.message ?: Exception("Unable to load items"))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TVSeries>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            val anchorPage = state.closestPageToPosition(anchorPosition)
            anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
        }
    }
}
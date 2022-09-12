package com.example.movieplayer.ui.tvseries.search

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieplayer.common.Result
import com.example.movieplayer.feature.fetchtvseries.data.TVSeries
import com.example.movieplayer.feature.fetchtvseries.domain.SearchTVSeriesUseCase
import com.example.movieplayer.ui.common.TooShortQueryException
import com.example.movieplayer.ui.common.getNextPageKey
import com.example.movieplayer.ui.tvseries.getTVSeriesRefreshKey

class SearchTVSeriesPagingSource(
    val searchTVSeriesUseCase: SearchTVSeriesUseCase,
    private val query: String
) : PagingSource<Int, TVSeries>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVSeries> {
        if (query.length <= 3) {
            return LoadResult.Error(TooShortQueryException)
        }

        val nextPageNumber = params.key ?: 1
        val response = searchTVSeriesUseCase(query, nextPageNumber)
        return when (response) {
            is Result.Success -> {
                LoadResult.Page(
                    data = response.data.items,
                    prevKey = null,
                    nextKey = response.data.getNextPageKey()
                )
            }
            is Result.Error -> {
                Log.d("SearchTVSeriesPagingSource", response.message.toString())
                LoadResult.Error(response.message ?: Exception("Unable to load items"))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TVSeries>): Int? {
        return getTVSeriesRefreshKey(state)
    }
}

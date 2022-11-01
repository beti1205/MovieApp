package com.beti1205.movieapp.ui.tvseries.search

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries
import com.beti1205.movieapp.feature.fetchtvseries.domain.SearchTVSeriesUseCase
import com.beti1205.movieapp.ui.common.TooShortQueryException
import com.beti1205.movieapp.ui.common.getNextPageKey
import com.beti1205.movieapp.ui.tvseries.list.getTVSeriesRefreshKey

class SearchTVSeriesPagingSource(
    val searchTVSeriesUseCase: SearchTVSeriesUseCase,
    private val query: String
) : PagingSource<Int, TVSeries>() {

    companion object {
        const val MIN_QUERY_LENGTH = 3
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVSeries> {
        if (query.length <= MIN_QUERY_LENGTH) {
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

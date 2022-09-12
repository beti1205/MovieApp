package com.example.movieplayer.ui.tvseries

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.movieplayer.common.Result
import com.example.movieplayer.feature.fetchtvseries.data.TVSeries
import com.example.movieplayer.feature.fetchtvseries.domain.FetchTVSeriesUseCase
import com.example.movieplayer.feature.fetchtvseries.domain.TVOrder

class TVSeriesPagingSource(
    val fetchTVSeriesUseCase: FetchTVSeriesUseCase,
    private val tvOrder: TVOrder
) : PagingSource<Int, TVSeries>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, TVSeries> {
        val nextPageNumber = params.key ?: 1
        val response = fetchTVSeriesUseCase(tvOrder, nextPageNumber)
        return when (response) {
            is Result.Success -> LoadResult.Page(
                data = response.data.items,
                prevKey = null,
                nextKey = response.data.page?.plus(1)
            )
            is Result.Error -> {
                Log.d("TVSeriesPagingSource", response.message.toString())
                LoadResult.Error(response.message ?: Exception("Unable to load items"))
            }
        }
    }

    override fun getRefreshKey(state: PagingState<Int, TVSeries>): Int? {
        return getTVSeriesRefreshKey(state)
    }
}

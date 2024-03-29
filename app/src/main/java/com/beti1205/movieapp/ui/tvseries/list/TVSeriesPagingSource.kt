/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.list

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.feature.tvseries.domain.FetchTVSeriesUseCase
import com.beti1205.movieapp.feature.tvseries.domain.TVOrder
import com.beti1205.movieapp.ui.common.getNextPageKey

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
                nextKey = response.data.getNextPageKey()
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

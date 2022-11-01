package com.beti1205.movieapp.ui.tvseries.list

import androidx.paging.PagingState
import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeries

fun getTVSeriesRefreshKey(state: PagingState<Int, TVSeries>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
        val anchorPage = state.closestPageToPosition(anchorPosition)
        anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
}

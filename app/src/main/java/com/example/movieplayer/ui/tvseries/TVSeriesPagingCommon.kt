package com.example.movieplayer.ui.tvseries

import androidx.paging.PagingState
import com.example.movieplayer.feature.fetchtvseries.data.TVSeries

fun getTVSeriesRefreshKey(state: PagingState<Int, TVSeries>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
        val anchorPage = state.closestPageToPosition(anchorPosition)
        anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
}

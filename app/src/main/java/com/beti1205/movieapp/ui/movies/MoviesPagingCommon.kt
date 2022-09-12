package com.beti1205.movieapp.ui.movies

import androidx.paging.PagingState
import com.beti1205.movieapp.feature.fetchmovies.data.Movie

fun getMovieRefreshKey(state: PagingState<Int, Movie>): Int? {
    return state.anchorPosition?.let { anchorPosition ->
        val anchorPage = state.closestPageToPosition(anchorPosition)
        anchorPage?.prevKey?.plus(1) ?: anchorPage?.nextKey?.minus(1)
    }
}

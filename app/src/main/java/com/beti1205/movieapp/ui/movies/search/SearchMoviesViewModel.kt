/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.feature.movies.domain.SearchMoviesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchMoviesViewModel @Inject constructor(
    private val searchMoviesUseCase: SearchMoviesUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    val queryFlow = savedStateHandle.getStateFlow("query", "")

    companion object {
        private const val TIMEOUT = 300L
    }

    @OptIn(ExperimentalCoroutinesApi::class, FlowPreview::class)
    val querySearchResults = queryFlow
        .debounce(TIMEOUT)
        .flatMapLatest { query ->
            getSearchResultStream(query)
        }.cachedIn(viewModelScope)

    private fun getSearchResultStream(
        query: String
    ): Flow<PagingData<Movie>> {
        return Pager(
            PagingConfig(pageSize = 20)
        ) {
            SearchMoviesPagingSource(searchMoviesUseCase, query)
        }.flow
    }

    fun onQueryChanged(query: String) {
        savedStateHandle["query"] = query
    }
}

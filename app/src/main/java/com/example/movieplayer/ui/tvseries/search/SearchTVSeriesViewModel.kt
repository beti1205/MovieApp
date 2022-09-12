package com.example.movieplayer.ui.tvseries.search

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieplayer.feature.fetchtvseries.data.TVSeries
import com.example.movieplayer.feature.fetchtvseries.domain.SearchTVSeriesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class SearchTVSeriesViewModel @Inject constructor(
    private val searchTVSeriesUseCase: SearchTVSeriesUseCase,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val queryFlow = savedStateHandle.getStateFlow("tv_query", "")

    @OptIn(ExperimentalCoroutinesApi::class)
    val querySearchResults = queryFlow
        .debounce(300)
        .flatMapLatest { query ->
            getSearchResultStream(query)
        }.cachedIn(viewModelScope)

    private fun getSearchResultStream(
        query: String
    ): Flow<PagingData<TVSeries>> {
        return Pager(
            PagingConfig(pageSize = 20)
        ) {
            SearchTVSeriesPagingSource(searchTVSeriesUseCase, query)
        }.flow
    }

    fun onQueryChanged(query: String) {
        savedStateHandle["tv_query"] = query
    }
}

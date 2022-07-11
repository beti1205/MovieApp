package com.example.movieplayer.ui.tvseries

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.example.movieplayer.feature.fetchtvseries.data.TVSeries
import com.example.movieplayer.feature.fetchtvseries.data.TVSeriesPagingSource
import com.example.movieplayer.feature.fetchtvseries.domain.FetchTVSeriesUseCase
import com.example.movieplayer.feature.fetchtvseries.domain.TVOrder
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TVSeriesViewModel @Inject constructor(
    private val fetchTVSeriesUseCase: FetchTVSeriesUseCase
) : ViewModel() {

    private val _tvSeries = MutableLiveData<List<TVSeries>>()
    val tvSeries: LiveData<List<TVSeries>> = _tvSeries

    private val _order: MutableStateFlow<TVOrder> = MutableStateFlow(TVOrder.POPULAR)
    private val order = _order.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val flow: Flow<PagingData<TVSeries>> = order.flatMapLatest { order ->
        Pager(
            PagingConfig(pageSize = 20)
        ) {
            TVSeriesPagingSource(fetchTVSeriesUseCase, order)
        }
        .flow
        .cachedIn(viewModelScope)
    }

    fun onOrderChanged(order: TVOrder) {
        _order.value = order
    }
}
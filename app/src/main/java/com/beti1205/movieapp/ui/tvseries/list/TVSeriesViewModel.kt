/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.cachedIn
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.feature.tvseries.domain.FetchTVSeriesUseCase
import com.beti1205.movieapp.feature.tvseries.domain.TVOrder
import com.beti1205.movieapp.ui.common.Preferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class TVSeriesViewModel @Inject constructor(
    private val fetchTVSeriesUseCase: FetchTVSeriesUseCase,
    private val preferences: Preferences
) : ViewModel() {

    private val _order: MutableStateFlow<TVOrder> = MutableStateFlow(TVOrder.POPULAR)
    val order = _order.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val tvSeries: Flow<PagingData<TVSeries>> = order.flatMapLatest { order ->
        Pager(
            PagingConfig(pageSize = 20)
        ) {
            TVSeriesPagingSource(fetchTVSeriesUseCase, order)
        }.flow
    }.cachedIn(viewModelScope)

    init {
        _order.value = TVOrder.from(preferences.tvOrder)
    }

    fun onOrderChanged(order: TVOrder) {
        _order.value = order
        preferences.tvOrder = order.ordinal
    }
}

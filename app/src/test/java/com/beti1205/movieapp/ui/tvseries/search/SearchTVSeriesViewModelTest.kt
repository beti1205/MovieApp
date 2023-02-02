/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.search

import androidx.lifecycle.SavedStateHandle
import androidx.paging.AsyncPagingDataDiffer
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.feature.tvseries.domain.SearchTVSeriesUseCase
import com.beti1205.movieapp.ui.NoopListCallback
import com.beti1205.movieapp.ui.TVSeriesDataProvider
import com.beti1205.movieapp.ui.tvseries.TVSeriesDiffCallback
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchTVSeriesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SearchTVSeriesViewModel
    private val searchTVSeriesUseCase = mockk<SearchTVSeriesUseCase>()
    private lateinit var differ: AsyncPagingDataDiffer<TVSeries>

    @Before
    fun setup() {
        differ = AsyncPagingDataDiffer(
            diffCallback = TVSeriesDiffCallback(),
            updateCallback = NoopListCallback(),
            workerDispatcher = Dispatchers.Main
        )
    }

    @Test
    fun fetchSearchedTVSeries_successful() = runTest {
        coEvery {
            searchTVSeriesUseCase(
                any(),
                any()
            )
        } returns Result.Success(TVSeriesDataProvider.apiResponse)
        viewModel = SearchTVSeriesViewModel(
            searchTVSeriesUseCase,
            SavedStateHandle(
                mapOf("tv_query" to query)
            )
        )

        val expectedTVSeriesList = TVSeriesDataProvider.apiResponse.items
        val job = launch {
            viewModel.querySearchResults.collectLatest {
                differ.submitData(it)
            }
        }

        advanceUntilIdle()

        assertEquals(expectedTVSeriesList, differ.snapshot().items)

        job.cancel()
    }

    @Test
    fun fetchSearchedTVSeries_verifyThatErrorOccurred_listIsEmpty() = runTest {
        coEvery { searchTVSeriesUseCase(any(), any()) } returns Result.Error(Exception())
        viewModel = SearchTVSeriesViewModel(
            searchTVSeriesUseCase,
            SavedStateHandle(
                mapOf("tv_query" to query)
            )
        )

        val job = launch {
            viewModel.querySearchResults.collectLatest {
                differ.submitData(it)
            }
        }

        advanceUntilIdle()

        assertTrue(differ.snapshot().items.isEmpty())

        job.cancel()
    }

    @Test
    fun verifyThatQueryWasSet() = runTest {
        viewModel = SearchTVSeriesViewModel(
            searchTVSeriesUseCase,
            SavedStateHandle(
                mapOf("tv_query" to query)
            )
        )

        assertEquals(query, viewModel.queryFlow.value)
    }

    @Test
    fun verifyThatQueryWasChanged() = runTest {
        val newQuery = "Star Wars"

        viewModel = SearchTVSeriesViewModel(
            searchTVSeriesUseCase,
            SavedStateHandle(
                mapOf("tv_query" to query)
            )
        )
        viewModel.onQueryChanged(newQuery)

        assertEquals(newQuery, viewModel.queryFlow.value)
    }

    companion object {
        private const val query = "House of the Dragon"
    }
}

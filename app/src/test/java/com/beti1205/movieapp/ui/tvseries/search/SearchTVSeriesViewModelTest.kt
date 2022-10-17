package com.beti1205.movieapp.ui.tvseries.search

import android.os.Build
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchtvseries.domain.SearchTVSeriesUseCase
import com.beti1205.movieapp.ui.tvseries.TVSeriesAdapter
import com.beti1205.movieapp.ui.tvseries.TVSeriesDataProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.R])
@OptIn(ExperimentalCoroutinesApi::class)
class SearchTVSeriesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SearchTVSeriesViewModel
    private val searchTVSeriesUseCase = mockk<SearchTVSeriesUseCase>()

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

        val adapter = TVSeriesAdapter(onClick = { _, _ -> })
        val expectedTVSeriesList = TVSeriesDataProvider.apiResponse.items
        val job = launch {
            viewModel.querySearchResults.collectLatest {
                adapter.submitData(it)
            }
        }

        advanceUntilIdle()

        assertEquals(expectedTVSeriesList, adapter.snapshot())

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

        val adapter = TVSeriesAdapter(onClick = { _, _ -> })
        val job = launch {
            viewModel.querySearchResults.collectLatest {
                adapter.submitData(it)
            }
        }

        advanceUntilIdle()

        assertTrue(adapter.snapshot().isEmpty())

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

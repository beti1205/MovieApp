package com.beti1205.movieapp.ui.tvseries

import android.os.Build
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchtvseries.domain.FetchTVSeriesUseCase
import com.beti1205.movieapp.feature.fetchtvseries.domain.TVOrder
import com.beti1205.movieapp.ui.common.Preferences
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
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
class TVSeriesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: TVSeriesViewModel
    private val fetchTVSeriesUseCase = mockk<FetchTVSeriesUseCase>()
    private val preferences = mockk<Preferences>()

    @Test
    fun fetchTvSeries_successful() = runTest {
        coEvery { fetchTVSeriesUseCase(any(), any()) } returns Result.Success(
            TVSeriesDataProvider.apiResponse
        )
        every { preferences.tvOrder } returns -1

        viewModel = TVSeriesViewModel(fetchTVSeriesUseCase, preferences)
        val adapter = TVSeriesAdapter(onClick = { _, _ -> })
        val expectedTvSeriesList = TVSeriesDataProvider.apiResponse.items
        val job = launch {
            viewModel.tvSeries.collectLatest {
                adapter.submitData(it)
            }
        }

        advanceUntilIdle()

        assertEquals(expectedTvSeriesList, adapter.snapshot())

        job.cancel()
    }

    @Test
    fun fetchTvSeries_verifyThatErrorOccurred_listIsEmpty() = runTest {
        coEvery { fetchTVSeriesUseCase(any(), any()) } returns Result.Error(Exception())
        every { preferences.tvOrder } returns -1

        viewModel = TVSeriesViewModel(fetchTVSeriesUseCase, preferences)
        val adapter = TVSeriesAdapter(onClick = { _, _ -> })
        val job = launch {
            viewModel.tvSeries.collectLatest {
                adapter.submitData(it)
            }
        }

        advanceUntilIdle()

        assertTrue(adapter.snapshot().isEmpty())

        job.cancel()
    }

    @Test
    fun tvOrder_verifyThatOrderHasChanged() = runTest {
        val slot = slot<Int>()
        every { preferences.tvOrder = capture(slot) } answers {}
        every { preferences.tvOrder } returns -1

        viewModel = TVSeriesViewModel(fetchTVSeriesUseCase, preferences)
        val newOrder = TVOrder.AIRING_TODAY
        viewModel.onOrderChanged(newOrder)

        every { preferences.tvOrder } returns slot.captured

        assertEquals(newOrder, viewModel.order.value)
        assertEquals(newOrder, TVOrder.from(preferences.tvOrder))
    }
}

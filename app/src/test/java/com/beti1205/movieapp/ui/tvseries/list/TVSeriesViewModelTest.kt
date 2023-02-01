package com.beti1205.movieapp.ui.tvseries.list

import androidx.paging.AsyncPagingDataDiffer
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.feature.tvseries.domain.FetchTVSeriesUseCase
import com.beti1205.movieapp.feature.tvseries.domain.TVOrder
import com.beti1205.movieapp.ui.NoopListCallback
import com.beti1205.movieapp.ui.TVSeriesDataProvider
import com.beti1205.movieapp.ui.common.Preferences
import com.beti1205.movieapp.ui.tvseries.TVSeriesDiffCallback
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
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
class TVSeriesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: TVSeriesViewModel
    private val fetchTVSeriesUseCase = mockk<FetchTVSeriesUseCase>()
    private val preferences = mockk<Preferences>()
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
    fun fetchTvSeries_successful() = runTest {
        coEvery { fetchTVSeriesUseCase(any(), any()) } returns Result.Success(
            TVSeriesDataProvider.apiResponse
        )
        every { preferences.tvOrder } returns -1

        viewModel = TVSeriesViewModel(fetchTVSeriesUseCase, preferences)
        val expectedTvSeriesList = TVSeriesDataProvider.apiResponse.items
        val job = launch {
            viewModel.tvSeries.collectLatest {
                differ.submitData(it)
            }
        }

        advanceUntilIdle()

        assertEquals(expectedTvSeriesList, differ.snapshot().items)

        job.cancel()
    }

    @Test
    fun fetchTvSeries_verifyThatErrorOccurred_listIsEmpty() = runTest {
        coEvery { fetchTVSeriesUseCase(any(), any()) } returns Result.Error(Exception())
        every { preferences.tvOrder } returns -1

        viewModel = TVSeriesViewModel(fetchTVSeriesUseCase, preferences)
        val job = launch {
            viewModel.tvSeries.collectLatest {
                differ.submitData(it)
            }
        }

        advanceUntilIdle()

        assertTrue(differ.snapshot().items.isEmpty())

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

package com.beti1205.movieapp.ui.tvseries.reviews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.reviews.domain.FetchTVSeriesReviewsUseCase
import com.beti1205.movieapp.ui.ReviewsDataProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TVSeriesReviewsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: TVSeriesReviewsViewModel
    private val fetchTVSeriesReviewsUseCase = mockk<FetchTVSeriesReviewsUseCase>()

    @Test
    fun fetchTVSeriesReviews_successful() = runTest {
        coEvery { fetchTVSeriesReviewsUseCase(any()) } returns Result.Success(
            ReviewsDataProvider.reviewResult
        )

        viewModel = TVSeriesReviewsViewModel(
            fetchTVSeriesReviewsUseCase,
            SavedStateHandle(mapOf("tvSeriesId" to ReviewsDataProvider.reviewResult.id))
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.reviews.collect() }

        assertEquals(ReviewsDataProvider.reviewResult.results, viewModel.reviews.value)

        collectJob.cancel()
    }

    @Test
    fun fetchTVSeriesReviews_failure() = runTest {
        coEvery { fetchTVSeriesReviewsUseCase(any()) } returns Result.Error(Exception())

        viewModel = TVSeriesReviewsViewModel(
            fetchTVSeriesReviewsUseCase,
            SavedStateHandle(mapOf("tvSeriesId" to ReviewsDataProvider.reviewResult.id))
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.reviewsError.collect() }

        assertTrue(viewModel.reviewsError.value)

        collectJob.cancel()
    }
}

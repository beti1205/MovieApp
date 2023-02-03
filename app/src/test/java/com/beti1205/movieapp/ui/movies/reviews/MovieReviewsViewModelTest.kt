/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.reviews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.reviews.domain.FetchMovieReviewsUseCase
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
class MovieReviewsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieReviewsViewModel
    private val fetchMovieReviewsUseCase = mockk<FetchMovieReviewsUseCase>()

    @Test
    fun fetchMovieReviews_successful() = runTest {
        coEvery { fetchMovieReviewsUseCase(any()) } returns Result.Success(
            ReviewsDataProvider.reviewResult
        )

        viewModel = MovieReviewsViewModel(
            fetchMovieReviewsUseCase,
            SavedStateHandle(mapOf("movieId" to ReviewsDataProvider.reviewResult.id))
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.reviews.collect() }

        assertEquals(ReviewsDataProvider.reviewResult.results, viewModel.reviews.value)

        collectJob.cancel()
    }

    @Test
    fun fetchMovieReviews_failure() = runTest {
        coEvery { fetchMovieReviewsUseCase(any()) } returns Result.Error(Exception())

        viewModel = MovieReviewsViewModel(
            fetchMovieReviewsUseCase,
            SavedStateHandle(mapOf("movieId" to ReviewsDataProvider.reviewResult.id))
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.reviewsError.collect() }

        assertTrue(viewModel.reviewsError.value)

        collectJob.cancel()
    }
}

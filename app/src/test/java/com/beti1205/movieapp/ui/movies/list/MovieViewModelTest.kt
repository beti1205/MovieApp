/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.list

import androidx.paging.AsyncPagingDataDiffer
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.feature.movies.domain.FetchMoviesUseCase
import com.beti1205.movieapp.feature.movies.domain.MovieOrder
import com.beti1205.movieapp.ui.MovieDataProvider
import com.beti1205.movieapp.ui.NoopListCallback
import com.beti1205.movieapp.ui.common.Preferences
import com.beti1205.movieapp.ui.movies.MovieDiffCallback
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
class MovieViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: MovieViewModel
    private val fetchMoviesUseCase = mockk<FetchMoviesUseCase>()
    private val preferences = mockk<Preferences>()
    private lateinit var differ: AsyncPagingDataDiffer<Movie>

    @Before
    fun setup() {
        differ = AsyncPagingDataDiffer(
            diffCallback = MovieDiffCallback(),
            updateCallback = NoopListCallback(),
            workerDispatcher = Dispatchers.Main
        )
    }

    @Test
    fun fetchMovies_successful() = runTest {
        coEvery {
            fetchMoviesUseCase(
                any(),
                any()
            )
        } returns Result.Success(MovieDataProvider.apiResponse)
        every { preferences.movieOrder } returns -1

        viewModel = MovieViewModel(fetchMoviesUseCase, preferences)
        val expectedMovieList = MovieDataProvider.movies
        val job = launch {
            viewModel.movies.collectLatest {
                differ.submitData(it)
            }
        }
        advanceUntilIdle()

        assertEquals(expectedMovieList, differ.snapshot().items)

        job.cancel()
    }

    @Test
    fun fetchMovies_verifyThatErrorOccurred_listIsEmpty() = runTest {
        coEvery { fetchMoviesUseCase(any(), any()) } returns Result.Error(Exception())
        every { preferences.movieOrder } returns -1

        viewModel = MovieViewModel(fetchMoviesUseCase, preferences)
        val job = launch {
            viewModel.movies.collectLatest {
                differ.submitData(it)
            }
        }
        advanceUntilIdle()

        assertTrue(differ.snapshot().items.isEmpty())

        job.cancel()
    }

    @Test
    fun verifyThatMovieOrderHasChanged() = runTest {
        val slot = slot<Int>()
        every { preferences.movieOrder = capture(slot) } answers {}
        every { preferences.movieOrder } returns -1

        viewModel = MovieViewModel(fetchMoviesUseCase, preferences)
        val newOrder = MovieOrder.TOP_RATED
        viewModel.onOrderChanged(newOrder)

        every { preferences.movieOrder } returns slot.captured

        assertEquals(newOrder, viewModel.order.value)
        assertEquals(newOrder, MovieOrder.from(preferences.movieOrder))
    }
}

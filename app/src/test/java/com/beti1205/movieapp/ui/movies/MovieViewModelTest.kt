package com.beti1205.movieapp.ui.movies

import android.os.Build
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchmovies.domain.FetchMoviesUseCase
import com.beti1205.movieapp.feature.fetchmovies.domain.MovieOrder
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
class MovieViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: MovieViewModel
    private val fetchMoviesUseCase = mockk<FetchMoviesUseCase>()
    private val preferences = mockk<Preferences>()

    @Test
    fun fetchMovies_successful() = runTest {
        coEvery { fetchMoviesUseCase(any(), any()) } returns Result.Success(MovieDataProvider.apiResponse)
        every { preferences.movieOrder } returns -1

        viewModel = MovieViewModel(fetchMoviesUseCase, preferences)
        val adapter = MovieAdapter(onClick = { _, _ -> })
        val expectedMovieList = MovieDataProvider.movies
        val job = launch {
            viewModel.movies.collectLatest {
                adapter.submitData(it)
            }
        }
        advanceUntilIdle()

        assertEquals(expectedMovieList, adapter.snapshot())

        job.cancel()
    }

    @Test
    fun fetchMovies_verifyThatErrorOccurred_listIsEmpty() = runTest {
        coEvery { fetchMoviesUseCase(any(), any()) } returns Result.Error(Exception())
        every { preferences.movieOrder } returns -1

        viewModel = MovieViewModel(fetchMoviesUseCase, preferences)
        val adapter = MovieAdapter(onClick = { _, _ -> })
        val job = launch {
            viewModel.movies.collectLatest {
                adapter.submitData(it)
            }
        }
        advanceUntilIdle()

        assertTrue(adapter.snapshot().isEmpty())

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

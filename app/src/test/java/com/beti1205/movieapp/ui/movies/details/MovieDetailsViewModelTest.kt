package com.beti1205.movieapp.ui.movies.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchcredits.domain.FetchMovieCreditsUseCase
import com.beti1205.movieapp.feature.fetchmoviedetails.domain.FetchMovieDetailsUseCase
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
class MovieDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieDetailsViewModel
    private val fetchMovieCreditsUseCase = mockk<FetchMovieCreditsUseCase>()
    private val fetchMovieDetailsUseCase = mockk<FetchMovieDetailsUseCase>()

    @Test
    fun fetchMovieDetails_successful() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieCreditsSuccess
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.state.collect() }
        }

        assertEquals(MovieDetailsDataProvider.movieDetailsScreenState, viewModel.state.value)

        collectJob.cancel()
    }

    @Test
    fun fetchMovieDetails_failure_movieCreditsError() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieError
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.state.collect() }
        }

        assertTrue(viewModel.state.value.hasError)

        collectJob.cancel()
    }

    @Test
    fun fetchMovieDetails_failure_movieDetailsError() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieCreditsSuccess
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieError
        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.state.collect() }
        }

        assertTrue(viewModel.state.value.hasError)

        collectJob.cancel()
    }

    @Test
    fun fetchMovieDetails_failure() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieError
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieError
        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.state.collect() }
        }

        assertTrue(viewModel.state.value.hasError)

        collectJob.cancel()
    }

    @Test
    fun verifyThatSelectedMovieIdWasSet() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieCreditsSuccess
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.selectedMovieId.collect() }

        assertEquals(MovieDetailsDataProvider.movieDetails.id, viewModel.selectedMovieId.value)

        collectJob.cancel()
    }

    private companion object {
        val movieDetailsSuccess = Result.Success(MovieDetailsDataProvider.movieDetails)
        val movieCreditsSuccess = Result.Success(MovieDetailsDataProvider.credits)
        val movieError = Result.Error(Exception())
    }
}

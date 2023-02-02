/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.accountstates.domain.FetchMoviesAccountStatesUseCase
import com.beti1205.movieapp.feature.credits.domain.FetchMovieCreditsUseCase
import com.beti1205.movieapp.feature.favorite.domain.MarkFavoriteUseCase
import com.beti1205.movieapp.feature.favorite.domain.MediaType
import com.beti1205.movieapp.feature.moviedetails.domain.FetchMovieDetailsUseCase
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
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
    private val markFavoriteUseCase = mockk<MarkFavoriteUseCase>()
    private val fetchMoviesAccountStatesUseCase = mockk<FetchMoviesAccountStatesUseCase>()
    private val authManager = mockk<AuthManager>()

    @Test
    fun fetchMovieDetails_successful() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieCreditsSuccess
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        coEvery { fetchMoviesAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase,
            markFavoriteUseCase,
            fetchMoviesAccountStatesUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.state.collect() }

        assertEquals(MovieDetailsDataProvider.movieDetailsScreenState, viewModel.state.value)

        collectJob.cancel()
    }

    @Test
    fun fetchMovieDetails_failure_movieCreditsError() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieError
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        coEvery { fetchMoviesAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase,
            markFavoriteUseCase,
            fetchMoviesAccountStatesUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.state.collect() }

        assertTrue(viewModel.state.value.hasError)

        collectJob.cancel()
    }

    @Test
    fun fetchMovieDetails_failure_movieDetailsError() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieCreditsSuccess
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieError
        coEvery { fetchMoviesAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase,
            markFavoriteUseCase,
            fetchMoviesAccountStatesUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.state.collect() }

        assertTrue(viewModel.state.value.hasError)

        collectJob.cancel()
    }

    @Test
    fun fetchMovieDetails_failure() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieError
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieError
        coEvery { fetchMoviesAccountStatesUseCase(any()) } returns movieError
        every { authManager.isLoggedInFlow } returns flowOf(false)

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase,
            markFavoriteUseCase,
            fetchMoviesAccountStatesUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.state.collect() }

        assertTrue(viewModel.state.value.hasError)

        collectJob.cancel()
    }

    @Test
    fun markFavorite_successful() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieCreditsSuccess
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        coEvery { fetchMoviesAccountStatesUseCase(any()) } returns accountStatusSuccess
        coEvery { markFavoriteUseCase(any(), any(), any()) } returns Result.Success(Unit)
        every { authManager.isLoggedInFlow } returns flowOf(true)
        every { authManager.isLoggedIn } returns true

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase,
            markFavoriteUseCase,
            fetchMoviesAccountStatesUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.state.collect() }

        viewModel.markFavorite(true)

        coVerify {
            markFavoriteUseCase(
                true,
                MediaType.MOVIE,
                MovieDetailsDataProvider.movieDetails.id
            )
        }

        assertTrue(viewModel.state.value.favorite)

        collectJob.cancel()
    }

    @Test
    fun markFavorite_failure() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieCreditsSuccess
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        coEvery { fetchMoviesAccountStatesUseCase(any()) } returns accountStatusSuccess
        coEvery { markFavoriteUseCase(any(), any(), any()) } returns Result.Error(Exception())
        every { authManager.isLoggedInFlow } returns flowOf(true)
        every { authManager.isLoggedIn } returns true

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase,
            markFavoriteUseCase,
            fetchMoviesAccountStatesUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.state.collect() }

        viewModel.markFavorite(true)

        coVerify {
            markFavoriteUseCase(
                true,
                MediaType.MOVIE,
                MovieDetailsDataProvider.movieDetails.id
            )
        }

        assertFalse(viewModel.state.value.favorite)
        assertTrue(viewModel.state.value.favoriteHasError)

        collectJob.cancel()
    }

    @Test
    fun onFavoriteErrorHandled_verifyThatErrorWasSet() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieCreditsSuccess
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        coEvery { fetchMoviesAccountStatesUseCase(any()) } returns accountStatusSuccess
        coEvery { markFavoriteUseCase(any(), any(), any()) } returns Result.Error(Exception())
        every { authManager.isLoggedInFlow } returns flowOf(true)
        every { authManager.isLoggedIn } returns true

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase,
            markFavoriteUseCase,
            fetchMoviesAccountStatesUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.state.collect() }

        viewModel.onFavoriteErrorHandled()

        assertFalse(viewModel.state.value.favoriteHasError)

        collectJob.cancel()
    }

    @Test
    fun verifyThatSelectedMovieIdWasSet() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieCreditsSuccess
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        coEvery { fetchMoviesAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase,
            markFavoriteUseCase,
            fetchMoviesAccountStatesUseCase,
            authManager
        )

        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.selectedMovieId.collect() }

        assertEquals(MovieDetailsDataProvider.movieDetails.id, viewModel.selectedMovieId.value)

        collectJob.cancel()
    }

    private companion object {
        val movieDetailsSuccess = Result.Success(MovieDetailsDataProvider.movieDetails)
        val movieCreditsSuccess = Result.Success(MovieDetailsDataProvider.credits)
        val accountStatusSuccess = Result.Success(MovieDetailsDataProvider.accountStates)
        val movieError = Result.Error(Exception())
    }
}

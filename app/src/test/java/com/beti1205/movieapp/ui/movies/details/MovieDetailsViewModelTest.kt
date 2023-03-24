/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.auth.AuthManager
import com.beti1205.movieapp.common.data.MediaType
import com.beti1205.movieapp.feature.combinedmoviedetails.domain.FetchCombinedMovieDetailsUseCase
import com.beti1205.movieapp.feature.favorite.domain.MarkFavoriteUseCase
import com.beti1205.movieapp.feature.watchlist.domain.AddToWatchlistUseCase
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
    private val fetchCombinedMovieDetailsUseCase = mockk<FetchCombinedMovieDetailsUseCase>()
    private val markFavoriteUseCase = mockk<MarkFavoriteUseCase>()
    private val addToWatchlistUseCase = mockk<AddToWatchlistUseCase>()
    private val authManager = mockk<AuthManager>()

    @Test
    fun fetchMovieDetails_successful() = runTest {
        coEvery { fetchCombinedMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchCombinedMovieDetailsUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.state.collect() }

        assertEquals(MovieDetailsDataProvider.movieDetailsScreenState, viewModel.state.value)

        collectJob.cancel()
    }

    @Test
    fun fetchMovieDetails_failure() = runTest {
        coEvery { fetchCombinedMovieDetailsUseCase(any()) } returns movieError
        every { authManager.isLoggedInFlow } returns flowOf(false)

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchCombinedMovieDetailsUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.state.collect() }

        assertTrue(viewModel.state.value.hasError)

        collectJob.cancel()
    }

    @Test
    fun markFavorite_successful() = runTest {
        coEvery { fetchCombinedMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        coEvery { markFavoriteUseCase(any(), any(), any()) } returns Result.Success(Unit)
        every { authManager.isLoggedInFlow } returns flowOf(true)
        every { authManager.isLoggedIn } returns true

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchCombinedMovieDetailsUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
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
        coEvery { fetchCombinedMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        coEvery { markFavoriteUseCase(any(), any(), any()) } returns Result.Error(Exception())
        every { authManager.isLoggedInFlow } returns flowOf(true)
        every { authManager.isLoggedIn } returns true

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchCombinedMovieDetailsUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
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
    fun addToWatchlist_successful() = runTest {
        coEvery { fetchCombinedMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        coEvery { addToWatchlistUseCase(any(), any(), any()) } returns Result.Success(Unit)
        every { authManager.isLoggedInFlow } returns flowOf(true)
        every { authManager.isLoggedIn } returns true

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchCombinedMovieDetailsUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.state.collect() }

        viewModel.addToWatchlist(true)

        coVerify {
            addToWatchlistUseCase(
                true,
                MediaType.MOVIE,
                MovieDetailsDataProvider.movieDetails.id
            )
        }

        assertTrue(viewModel.state.value.watchlist)

        collectJob.cancel()
    }

    @Test
    fun addToWatchlist_failure() = runTest {
        coEvery { fetchCombinedMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        coEvery { addToWatchlistUseCase(any(), any(), any()) } returns Result.Error(Exception())
        every { authManager.isLoggedInFlow } returns flowOf(true)
        every { authManager.isLoggedIn } returns true

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchCombinedMovieDetailsUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.state.collect() }

        viewModel.addToWatchlist(true)

        coVerify {
            addToWatchlistUseCase(
                true,
                MediaType.MOVIE,
                MovieDetailsDataProvider.movieDetails.id
            )
        }

        assertFalse(viewModel.state.value.watchlist)
        assertTrue(viewModel.state.value.watchlistError)

        collectJob.cancel()
    }

    @Test
    fun onFavoriteErrorHandled_verifyThatErrorWasSet() = runTest {
        coEvery { fetchCombinedMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        coEvery { markFavoriteUseCase(any(), any(), any()) } returns Result.Error(Exception())
        every { authManager.isLoggedInFlow } returns flowOf(true)
        every { authManager.isLoggedIn } returns true

        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovieId" to MovieDetailsDataProvider.movieDetails.id)),
            fetchCombinedMovieDetailsUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.state.collect() }

        viewModel.onFavoriteErrorHandled()

        assertFalse(viewModel.state.value.favoriteHasError)

        collectJob.cancel()
    }

    private companion object {
        val movieDetailsSuccess = Result.Success(MovieDetailsDataProvider.movieDetailsScreenState)
        val movieError = Result.Error(Exception())
    }
}

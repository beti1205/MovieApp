/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails
import com.beti1205.movieapp.feature.accountdetails.data.Avatar
import com.beti1205.movieapp.feature.accountdetails.data.Tmdb
import com.beti1205.movieapp.feature.accountdetails.domain.FetchAccountDetailsUseCase
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.feature.movies.domain.FavoriteListOrder
import com.beti1205.movieapp.feature.movies.domain.FetchFavoriteMoviesUseCase
import com.beti1205.movieapp.feature.session.data.SessionResponse
import com.beti1205.movieapp.feature.session.domain.CreateSessionUseCase
import com.beti1205.movieapp.feature.session.domain.DeleteSessionUseCase
import com.beti1205.movieapp.feature.token.data.RequestTokenResponse
import com.beti1205.movieapp.feature.token.domain.RequestTokenUseCase
import com.beti1205.movieapp.feature.tvseries.domain.FetchFavoriteTVSeriesUseCase
import com.beti1205.movieapp.ui.MovieDataProvider
import com.beti1205.movieapp.ui.TVSeriesDataProvider
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AccountViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: AccountViewModel
    private val requestTokenUseCase = mockk<RequestTokenUseCase>()
    private val createSessionUseCase = mockk<CreateSessionUseCase>()
    private val authManager = mockk<AuthManager>()
    private val deleteSessionUseCase = mockk<DeleteSessionUseCase>()
    private val fetchAccountDetailsUseCase = mockk<FetchAccountDetailsUseCase>()
    private val fetchFavoriteMoviesUseCase = mockk<FetchFavoriteMoviesUseCase>()
    private val fetchFavoriteTVSeriesUseCase = mockk<FetchFavoriteTVSeriesUseCase>()

    @Test
    fun fetchRequestToken_successful() = runTest {
        coEvery { requestTokenUseCase() } returns getTokenSuccess
        every { authManager.isLoggedInFlow } returns flowOf(false)
        val state = SavedStateHandle(mapOf("request_token" to null))

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        viewModel.fetchRequestToken()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.hasError.collect()
        }

        assertFalse(viewModel.hasError.value)
        assertEquals("requestToken", state["request_token"])

        collectJob.cancel()
    }

    @Test
    fun fetchRequestToken_failed() = runTest {
        coEvery { requestTokenUseCase() } returns Result.Error(Exception())
        every { authManager.isLoggedInFlow } returns flowOf(false)
        val state = SavedStateHandle(mapOf("request_token" to null))

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        viewModel.fetchRequestToken()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.hasError.collect()
        }

        assertTrue(viewModel.hasError.value)
        assertNull(state["request_token"])

        collectJob.cancel()
    }

    @Test
    fun createSession_successful() = runTest {
        coEvery { fetchAccountDetailsUseCase() } returns accountDetails
        coEvery { createSessionUseCase(any()) } returns createSessionSuccess
        val sessionIdSlot = slot<String>()
        every { authManager.setSessionId(sessionId = capture(sessionIdSlot)) } returns Unit
        every { authManager.isLoggedInFlow } returns flowOf(true)
        val state = SavedStateHandle(mapOf("approved" to true, "request_token" to "requestToken"))

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch {
                viewModel.hasError.collect()
            }
            launch {
                viewModel.isLoggedIn.collect()
            }
        }

        assertTrue(!viewModel.hasError.value)
        assertEquals("sessionId", sessionIdSlot.captured)
        assertTrue(viewModel.isLoggedIn.value)

        collectJob.cancel()
    }

    @Test
    fun createSession_failed() = runTest {
        coEvery { createSessionUseCase(any()) } returns Result.Error(Exception())
        every { authManager.isLoggedInFlow } returns flowOf(false)
        val state = SavedStateHandle(mapOf("approved" to true, "request_token" to "requestToken"))

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch {
                viewModel.hasError.collect()
            }
            launch {
                viewModel.isLoggedIn.collect()
            }
        }

        assertTrue(viewModel.hasError.value)
        assertFalse(viewModel.isLoggedIn.value)

        collectJob.cancel()
    }

    @Test
    fun deleteSession_successful() = runTest {
        coEvery { deleteSessionUseCase() } returns Result.Success(Unit)
        every { authManager.isLoggedInFlow } returns flowOf(false)
        val state = SavedStateHandle(mapOf("request_token" to "requestToken"))

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        viewModel.deleteSession()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.isLoggedIn.collect()
        }

        coVerify { deleteSessionUseCase() }
        assertFalse(viewModel.isLoggedIn.value)

        collectJob.cancel()
    }

    @Test
    fun deleteSession_failed() = runTest {
        coEvery { fetchAccountDetailsUseCase() } returns accountDetails
        coEvery { deleteSessionUseCase() } returns Result.Error(Exception())
        every { authManager.isLoggedInFlow } returns flowOf(true)
        val state = SavedStateHandle(mapOf("request_token" to null))

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        viewModel.deleteSession()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.hasError.collect() }
            launch { viewModel.isLoggedIn.collect() }
        }

        coVerify { deleteSessionUseCase() }
        assertTrue(viewModel.isLoggedIn.value)
        assertTrue(viewModel.hasError.value)

        collectJob.cancel()
    }

    @Test
    fun fetchAccountDetails_success() = runTest {
        coEvery { fetchAccountDetailsUseCase() } returns accountDetails
        every { authManager.isLoggedInFlow } returns flowOf(true)
        val state = SavedStateHandle()

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.account.collect()
        }

        assertEquals(accountDetails.data, viewModel.account.value)

        collectJob.cancel()
    }

    @Test
    fun fetchAccountDetails_failed() = runTest {
        coEvery { fetchAccountDetailsUseCase() } returns Result.Error(Exception())
        every { authManager.isLoggedInFlow } returns flowOf(true)
        val state = SavedStateHandle()

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.account.collect()
        }

        assertNull(viewModel.account.value)

        collectJob.cancel()
    }

    @Test
    fun fetchFavoriteMovies_success() = runTest {
        coEvery { fetchAccountDetailsUseCase() } returns accountDetails
        coEvery { fetchFavoriteMoviesUseCase(any()) } returns favoriteMovies
        every { authManager.isLoggedInFlow } returns flowOf(true)
        val state = SavedStateHandle()

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        viewModel.fetchFavoriteMovies()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.movies.collect()
        }

        assertEquals(favoriteMovies.data.items, viewModel.movies.value)

        collectJob.cancel()
    }

    @Test
    fun fetchFavoriteMovies_failed() = runTest {
        coEvery { fetchAccountDetailsUseCase() } returns accountDetails
        coEvery { fetchFavoriteMoviesUseCase(any()) } returns Result.Error(Exception())
        every { authManager.isLoggedInFlow } returns flowOf(true)
        val state = SavedStateHandle()

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        viewModel.fetchFavoriteMovies()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.movies.collect()
        }

        assertEquals(emptyList<Movie>(), viewModel.movies.value)

        collectJob.cancel()
    }

    @Test
    fun onOrderChanged_verifyThatOrderWasChangedAndMethodWasCalled() = runTest {
        coEvery { fetchAccountDetailsUseCase() } returns accountDetails
        coEvery { fetchFavoriteMoviesUseCase(any()) } returns favoriteMovies
        every { authManager.isLoggedInFlow } returns flowOf(true)
        val state = SavedStateHandle()

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        assertEquals(FavoriteListOrder.OLDEST, viewModel.order.value)

        viewModel.onOrderChanged(FavoriteListOrder.LATEST)

        assertEquals(FavoriteListOrder.LATEST, viewModel.order.value)
        coVerify { fetchFavoriteMoviesUseCase(FavoriteListOrder.LATEST) }
    }

    @Test
    fun fetchFavoriteTVSeries_success() = runTest {
        coEvery { fetchAccountDetailsUseCase() } returns accountDetails
        coEvery { fetchFavoriteTVSeriesUseCase() } returns favoriteTVSeries
        every { authManager.isLoggedInFlow } returns flowOf(true)
        val state = SavedStateHandle()

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        viewModel.fetchFavoriteTVSeries()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.tvSeries.collect()
        }

        assertEquals(favoriteTVSeries.data.items, viewModel.tvSeries.value)

        collectJob.cancel()
    }

    @Test
    fun fetchFavoriteTVSeries_failed() = runTest {
        coEvery { fetchAccountDetailsUseCase() } returns accountDetails
        coEvery { fetchFavoriteTVSeriesUseCase() } returns Result.Error(Exception())
        every { authManager.isLoggedInFlow } returns flowOf(true)
        val state = SavedStateHandle()

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        viewModel.fetchFavoriteTVSeries()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.tvSeries.collect()
        }

        assertEquals(emptyList<Movie>(), viewModel.tvSeries.value)

        collectJob.cancel()
    }

    @Test
    fun verifyThatMethodsWereCalledAfterLoggingIn() = runTest {
        coEvery { fetchAccountDetailsUseCase() } returns accountDetails
        coEvery { fetchFavoriteMoviesUseCase(any()) } returns favoriteMovies
        coEvery { fetchFavoriteTVSeriesUseCase() } returns favoriteTVSeries
        every { authManager.isLoggedInFlow } returns flowOf(true)
        val state = SavedStateHandle()

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.isLoggedIn.collect()
        }

        coVerify { fetchFavoriteMoviesUseCase(any()) }
        coVerify { fetchFavoriteTVSeriesUseCase() }
        coVerify { fetchAccountDetailsUseCase() }

        collectJob.cancel()
    }

    @Test
    fun onErrorHandled_verifyThatFalseWasSet() = runTest {
        coEvery { requestTokenUseCase() } returns Result.Error(Exception())
        every { authManager.isLoggedInFlow } returns flowOf(false)
        val state = SavedStateHandle(mapOf("request_token" to null))

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        viewModel.fetchRequestToken()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.hasError.collect()
        }

        assertTrue(viewModel.hasError.value)

        viewModel.onErrorHandled()

        assertFalse(viewModel.hasError.value)

        collectJob.cancel()
    }

    @Test
    fun onDeniedHandled_verifyThatFalseWasSet() = runTest {
        val state = SavedStateHandle(mapOf("denied" to true, "request_token" to null))
        every { authManager.isLoggedInFlow } returns flowOf(false)

        viewModel = AccountViewModel(
            state,
            requestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            fetchFavoriteMoviesUseCase,
            fetchFavoriteTVSeriesUseCase,
            authManager
        )

        viewModel.onDeniedHandled()

        assertFalse(state["denied"]!!)
    }

    companion object {
        val getTokenSuccess = Result.Success(
            RequestTokenResponse(
                success = true,
                expiresAt = "19:03:58",
                requestToken = "requestToken"
            )
        )
        val createSessionSuccess = Result.Success(
            SessionResponse(
                success = true,
                sessionId = "sessionId"
            )
        )
        val accountDetails = Result.Success(
            AccountDetails(
                id = 1,
                name = "Beata",
                username = "beti1205",
                avatar = Avatar(tmdb = Tmdb(avatarPath = null))
            )
        )

        val favoriteMovies = Result.Success(MovieDataProvider.apiResponse)
        val favoriteTVSeries = Result.Success(TVSeriesDataProvider.apiResponse)
    }
}

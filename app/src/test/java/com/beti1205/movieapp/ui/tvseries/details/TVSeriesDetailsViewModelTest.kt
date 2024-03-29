/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.details

import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.auth.AuthManager
import com.beti1205.movieapp.common.data.MediaType
import com.beti1205.movieapp.feature.accountstates.domain.FetchTVAccountStatesUseCase
import com.beti1205.movieapp.feature.credits.domain.FetchTVSeriesCreditsUseCase
import com.beti1205.movieapp.feature.favorite.domain.MarkFavoriteUseCase
import com.beti1205.movieapp.feature.tvepisodes.data.Episode
import com.beti1205.movieapp.feature.tvepisodes.domain.FetchEpisodesUseCase
import com.beti1205.movieapp.feature.tvseriesdetails.domain.FetchTVSeriesDetailsUseCase
import com.beti1205.movieapp.feature.watchlist.domain.AddToWatchlistUseCase
import com.beti1205.movieapp.ui.TVSeriesDataProvider
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
class TVSeriesDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: TVSeriesDetailsViewModel
    private val fetchTVSeriesDetailsUseCase = mockk<FetchTVSeriesDetailsUseCase>()
    private val fetchEpisodesUseCase = mockk<FetchEpisodesUseCase>()
    private val fetchTVSeriesCreditsUseCase = mockk<FetchTVSeriesCreditsUseCase>()
    private val markFavoriteUseCase = mockk<MarkFavoriteUseCase>()
    private val fetchTVAccountStatesUseCase = mockk<FetchTVAccountStatesUseCase>()
    private val addToWatchlistUseCase = mockk<AddToWatchlistUseCase>()
    private val authManager = mockk<AuthManager>()

    @Test
    fun fetchTVSeriesDetails_successful() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesEpisodesSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        coEvery { fetchTVAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase,
            fetchTVAccountStatesUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.hasError.collect() }
            launch { viewModel.tvSeriesDetails.collect() }
        }

        assertEquals(TVSeriesDetailsDataProvider.tvSeriesDetails, viewModel.tvSeriesDetails.value)
        assertTrue(!viewModel.hasError.value)

        collectJob.cancel()
    }

    @Test
    fun fetchTVSeriesDetails_failure() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesError
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesEpisodesSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        coEvery { fetchTVAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase,
            fetchTVAccountStatesUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.hasError.collect() }

        assertTrue(viewModel.hasError.value)

        collectJob.cancel()
    }

    @Test
    fun fetchEpisodes_successful() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesEpisodesSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        coEvery { fetchTVAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase,
            fetchTVAccountStatesUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.episodes.collect() }

        assertEquals(TVSeriesDetailsDataProvider.episodesList, viewModel.episodes.value)

        collectJob.cancel()
    }

    @Test
    fun fetchEpisodes_failure() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesError
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        coEvery { fetchTVAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase,
            fetchTVAccountStatesUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.episodes.collect() }

        assertEquals(emptyList<List<Episode>>(), viewModel.episodes.value)

        collectJob.cancel()
    }

    @Test
    fun fetchTVSeriesCredits_successful() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesEpisodesSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        coEvery { fetchTVAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase,
            fetchTVAccountStatesUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.credits.collect() }

        assertEquals(TVSeriesDetailsDataProvider.credits, viewModel.credits.value)

        collectJob.cancel()
    }

    @Test
    fun fetchTVSeriesCredits_failure() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesEpisodesSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesError
        coEvery { fetchTVAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase,
            fetchTVAccountStatesUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.credits.collect() }

        assertEquals(null, viewModel.credits.value)

        collectJob.cancel()
    }

    @Test
    fun fetchTVAccountStates_successful() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        coEvery { markFavoriteUseCase(any(), any(), any()) } returns Result.Success(Unit)
        coEvery { fetchTVAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedIn } returns true
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase,
            fetchTVAccountStatesUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.isFavorite.collect() }
            launch { viewModel.isAddedToWatchlist.collect() }
        }

        assertEquals(TVSeriesDetailsDataProvider.accountStates.favorite, viewModel.isFavorite.value)
        assertEquals(
            TVSeriesDetailsDataProvider.accountStates.watchlist,
            viewModel.isAddedToWatchlist.value
        )

        collectJob.cancel()
    }

    @Test
    fun markFavorite_successful() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        coEvery { markFavoriteUseCase(any(), any(), any()) } returns Result.Success(Unit)
        coEvery { fetchTVAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedIn } returns true
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase,
            fetchTVAccountStatesUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.isFavorite.collect() }

        viewModel.markFavorite(false)

        coVerify {
            markFavoriteUseCase(
                false,
                MediaType.TV,
                TVSeriesDataProvider.tvSeries.id
            )
        }

        assertFalse(viewModel.isFavorite.value)

        collectJob.cancel()
    }

    @Test
    fun markFavorite_failure() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        coEvery { markFavoriteUseCase(any(), any(), any()) } returns Result.Error(Exception())
        coEvery { fetchTVAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedIn } returns true
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase,
            fetchTVAccountStatesUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.favoriteHasError.collect() }
            launch { viewModel.isFavorite.collect() }
        }

        viewModel.markFavorite(false)

        assertTrue(viewModel.favoriteHasError.value)
        assertTrue(viewModel.isFavorite.value)

        collectJob.cancel()
    }

    @Test
    fun addToWatchlist_successful() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        coEvery { addToWatchlistUseCase(any(), any(), any()) } returns Result.Success(Unit)
        coEvery { fetchTVAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedIn } returns true
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase,
            fetchTVAccountStatesUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.isAddedToWatchlist.collect() }

        viewModel.addToWatchlist(false)

        coVerify {
            addToWatchlistUseCase(
                false,
                MediaType.TV,
                TVSeriesDataProvider.tvSeries.id
            )
        }

        assertFalse(viewModel.isAddedToWatchlist.value)

        collectJob.cancel()
    }

    @Test
    fun addToWatchlist_failure() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        coEvery { addToWatchlistUseCase(any(), any(), any()) } returns Result.Error(Exception())
        coEvery { fetchTVAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedIn } returns true
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase,
            fetchTVAccountStatesUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.isAddedToWatchlist.collect()
        }

        viewModel.addToWatchlist(false)

        assertTrue(viewModel.isAddedToWatchlist.value)
        assertTrue(viewModel.watchlistError.value)

        collectJob.cancel()
    }

    @Test
    fun onFavoriteErrorHandled_verifyThatFalseWasSet() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        coEvery { markFavoriteUseCase(any(), any(), any()) } returns Result.Error(Exception())
        coEvery { fetchTVAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedIn } returns true
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase,
            fetchTVAccountStatesUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.favoriteHasError.collect()
        }

        viewModel.onFavoriteErrorHandled()

        assertFalse(viewModel.favoriteHasError.value)

        collectJob.cancel()
    }

    @Test
    fun setSelectedSeasonPosition() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesEpisodesSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        coEvery { fetchTVAccountStatesUseCase(any()) } returns accountStatusSuccess
        every { authManager.isLoggedInFlow } returns flowOf(true)

        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase,
            fetchTVAccountStatesUseCase,
            markFavoriteUseCase,
            addToWatchlistUseCase,
            authManager
        )
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.selectedSeason.collect() }

        viewModel.setSelectedSeason(TVSeriesDetailsDataProvider.seasonsList.last())

        val selectedSeason = viewModel.selectedSeason.value
        assertEquals(TVSeriesDetailsDataProvider.seasonsList.last(), selectedSeason)

        collectJob.cancel()
    }

    companion object {
        val tvSeriesDetailsSuccess = Result.Success(TVSeriesDetailsDataProvider.tvSeriesDetails)
        val tvSeriesEpisodesSuccess = Result.Success(TVSeriesDetailsDataProvider.seasonResponse)
        val tvSeriesCreditsSuccess = Result.Success(TVSeriesDetailsDataProvider.credits)
        val accountStatusSuccess = Result.Success(TVSeriesDetailsDataProvider.accountStates)
        val tvSeriesError = Result.Error(Exception())
    }
}

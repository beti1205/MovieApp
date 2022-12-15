package com.beti1205.movieapp.ui.tvseries.details

import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchcredits.domain.FetchTVSeriesCreditsUseCase
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode
import com.beti1205.movieapp.feature.fetchtvepisodes.domain.FetchEpisodesUseCase
import com.beti1205.movieapp.feature.fetchtvseriesdetails.domain.FetchTVSeriesDetailsUseCase
import com.beti1205.movieapp.ui.tvseries.TVSeriesDataProvider
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
class TVSeriesDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: TVSeriesDetailsViewModel
    private val fetchTVSeriesDetailsUseCase = mockk<FetchTVSeriesDetailsUseCase>()
    private val fetchEpisodesUseCase = mockk<FetchEpisodesUseCase>()
    private val fetchTVSeriesCreditsUseCase = mockk<FetchTVSeriesCreditsUseCase>()

    @Test
    fun fetchTVSeriesDetails_successful() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesEpisodesSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase
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
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase
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
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase
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
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase
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
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase
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
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.credits.collect() }

        assertEquals(null, viewModel.credits.value)

        collectJob.cancel()
    }

    @Test
    fun verifyThatSelectedTVSeriesWasSet() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesEpisodesSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase
        )

        val collectJob =
            launch(UnconfinedTestDispatcher()) { viewModel.selectedTVSeriesId.collect() }

        assertEquals(TVSeriesDataProvider.tvSeries.id, viewModel.selectedTVSeriesId.value)

        collectJob.cancel()
    }

    @Test
    fun setSelectedSeasonPosition() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesEpisodesSuccess
        coEvery { fetchTVSeriesCreditsUseCase(any()) } returns tvSeriesCreditsSuccess
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeriesId" to TVSeriesDataProvider.tvSeries.id)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase,
            fetchTVSeriesCreditsUseCase
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
        val tvSeriesError = Result.Error(Exception())
    }
}

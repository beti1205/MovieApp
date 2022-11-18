package com.beti1205.movieapp.ui.tvseries.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
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
import org.junit.Assert.assertNotNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class TVSeriesDetailsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: TVSeriesDetailsViewModel
    private val fetchTVSeriesDetailsUseCase = mockk<FetchTVSeriesDetailsUseCase>()
    private val fetchEpisodesUseCase = mockk<FetchEpisodesUseCase>()

    @Test
    fun fetchSeasons_successful() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesEpisodesSuccess
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeries" to TVSeriesDataProvider.tvSeries)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase
        )

        assertEquals(TVSeriesDetailsDataProvider.seasonsList, viewModel.seasons.value)
        assertEquals(TVSeriesDetailsDataProvider.genresList, viewModel.genres.value)
        assertTrue(viewModel.hasError.value != true)
    }

    @Test
    fun fetchSeasons_failure() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesError
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesEpisodesSuccess
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeries" to TVSeriesDataProvider.tvSeries)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase
        )

        assertTrue(viewModel.hasError.value == true)
    }

    @Test
    fun fetchEpisodes_successful() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesEpisodesSuccess
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeries" to TVSeriesDataProvider.tvSeries)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.episodes.collect() }

        assertEquals(TVSeriesDetailsDataProvider.episodesList, viewModel.episodes.value)

        collectJob.cancel()
    }

    @Test
    fun fetchEpisodes_failure() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesError
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeries" to TVSeriesDataProvider.tvSeries)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.episodes.collect() }

        assertEquals(emptyList<List<Episode>>(), viewModel.episodes.value)

        collectJob.cancel()
    }

    @Test
    fun verifyThatSelectedTVSeriesWasSet() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesEpisodesSuccess
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeries" to TVSeriesDataProvider.tvSeries)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase
        )

        assertEquals(TVSeriesDataProvider.tvSeries, viewModel.selectedTVSeries.value)
    }

    @Test
    fun setSelectedSeasonPosition() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns tvSeriesDetailsSuccess
        coEvery { fetchEpisodesUseCase(any(), any()) } returns tvSeriesEpisodesSuccess
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeries" to TVSeriesDataProvider.tvSeries)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase
        )
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.selectedSeason.collect() }

        viewModel.setSelectedSeasonPosition(0)

        val selectedSeason = viewModel.selectedSeason.value
        assertNotNull(selectedSeason)
        assertEquals(0, viewModel.seasons.value?.indexOf(selectedSeason))

        collectJob.cancel()
    }

    companion object {
        val tvSeriesDetailsSuccess = Result.Success(TVSeriesDetailsDataProvider.tvSeriesDetails)
        val tvSeriesEpisodesSuccess = Result.Success(TVSeriesDetailsDataProvider.seasonResponse)
        val tvSeriesError = Result.Error(Exception())
    }
}

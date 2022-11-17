package com.beti1205.movieapp.ui.tvseries.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchtvepisodes.domain.FetchEpisodesUseCase
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Genre
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.Season
import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.TVSeriesDetails
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
import org.junit.Before
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

    @Before
    fun setup() {
        viewModel = TVSeriesDetailsViewModel(
            SavedStateHandle(mapOf("selectedTVSeries" to TVSeriesDataProvider.tvSeries)),
            fetchTVSeriesDetailsUseCase,
            fetchEpisodesUseCase
        )
    }

    @Test
    fun fetchSeasons_successful() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns Result.Success(tvSeriesDetails)

        viewModel.fetchSeasons(1)

        assertEquals(seasonsList, viewModel.seasons.value)
        assertEquals(genresList, viewModel.genres.value)
        assertTrue(viewModel.hasError.value != true)
    }

    @Test
    fun fetchSeasons_failure() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns Result.Error(Exception())

        viewModel.fetchSeasons(1)

        assertTrue(viewModel.hasError.value == true)
    }

    @Test
    fun verifyThatSelectedTVSeriesWasSet() = runTest {
        assertEquals(TVSeriesDataProvider.tvSeries, viewModel.selectedTVSeries.value)
    }

    @Test
    fun setSelectedSeasonPosition() = runTest {
        coEvery { fetchTVSeriesDetailsUseCase(any()) } returns Result.Success(tvSeriesDetails)
        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.selectedSeason.collect() }

        viewModel.fetchSeasons(1)
        viewModel.setSelectedSeasonPosition(0)

        val selectedSeason = viewModel.selectedSeason.value
        assertNotNull(selectedSeason)
        assertEquals(0, viewModel.seasons.value?.indexOf(selectedSeason))

        collectJob.cancel()
    }

    companion object {
        private val genresList = listOf(
            Genre(
                id = 18,
                name = "Drama"
            )
        )

        private val seasonsList = listOf(
            Season(
                airDate = "2021-09-21",
                episodeCount = 10,
                id = 170644,
                name = "Limited Series",
                overview = "In prison, Jeff's newfound fame makes him a target.",
                posterPath = "/h7YlJ1Mhg6jCZiHToUiKqHdzMO9.jpg",
                seasonNumber = 1
            ),
            Season(
                airDate = "2022-09-21",
                episodeCount = 10,
                id = 170645,
                name = "Limited Series",
                overview = "In prison, Jeff's newfound fame makes him a target.",
                posterPath = "/h7YlJ1Mhg6jCZiHToUiKqHdzMO9.jpg",
                seasonNumber = 2
            )
        )

        val tvSeriesDetails = TVSeriesDetails(
            firstAirDate = "2022-09-21",
            genres = genresList,
            inProduction = false,
            lastAirDate = "2022-09-21",
            numberOfEpisodes = 10,
            numberOfSeasons = 1,
            seasons = seasonsList
        )
    }
}

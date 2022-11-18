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
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
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
    fun fetchCredits_successful() {
        runTest {
            coEvery { fetchMovieCreditsUseCase(any()) } returns movieCreditsSuccess
            coEvery { fetchMovieDetailsUseCase(any()) } returns movieDetailsSuccess
            viewModel = MovieDetailsViewModel(
                SavedStateHandle(mapOf("selectedMovie" to MovieDetailsDataProvider.movie)),
                fetchMovieCreditsUseCase,
                fetchMovieDetailsUseCase
            )

            assertEquals(MovieDetailsDataProvider.cast, viewModel.cast.value)
            assertEquals(MovieDetailsDataProvider.crew, viewModel.crew.value)
            assertTrue(viewModel.hasError.value != true)
        }
    }

    @Test
    fun fetchCredits_failure() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieError
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovie" to MovieDetailsDataProvider.movie)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase
        )

        assertTrue(viewModel.hasError.value == true)
    }

    @Test
    fun fetchGenres_successful() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieCreditsSuccess
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovie" to MovieDetailsDataProvider.movie)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase
        )

        assertEquals(MovieDetailsDataProvider.genresList, viewModel.genres.value)
    }

    @Test
    fun fetchGenres_failure() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieCreditsSuccess
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieError
        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovie" to MovieDetailsDataProvider.movie)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase
        )

        assertNull(viewModel.genres.value)
    }

    @Test
    fun verifyThatSelectedMovieWasSet() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns movieCreditsSuccess
        coEvery { fetchMovieDetailsUseCase(any()) } returns movieDetailsSuccess
        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovie" to MovieDetailsDataProvider.movie)),
            fetchMovieCreditsUseCase,
            fetchMovieDetailsUseCase
        )

        assertEquals(MovieDetailsDataProvider.movie, viewModel.selectedMovie.value)
    }

    private companion object {
        val movieDetailsSuccess = Result.Success(MovieDetailsDataProvider.movieDetails)
        val movieCreditsSuccess = Result.Success(MovieDetailsDataProvider.credits)
        val movieError = Result.Error(Exception())
    }
}

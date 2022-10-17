package com.beti1205.movieapp.ui.movies.details

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.feature.fetchcredits.data.Credits
import com.beti1205.movieapp.feature.fetchcredits.data.Crew
import com.beti1205.movieapp.feature.fetchcredits.domain.FetchMovieCreditsUseCase
import com.beti1205.movieapp.feature.fetchmovies.data.Movie
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
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

    @Before
    fun setup() {
        viewModel = MovieDetailsViewModel(
            SavedStateHandle(mapOf("selectedMovie" to movie)),
            fetchMovieCreditsUseCase
        )
    }

    @Test
    fun fetchCredits_successful() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns Result.Success(credits)

        viewModel.fetchCredits(1)

        assertEquals(cast, viewModel.cast.value)
        assertEquals(crew, viewModel.crew.value)
        assertTrue(viewModel.hasError.value != true)
    }

    @Test
    fun fetchCredits_failure() = runTest {
        coEvery { fetchMovieCreditsUseCase(any()) } returns Result.Error(Exception())

        viewModel.fetchCredits(1)
        advanceUntilIdle()

        assertTrue(viewModel.hasError.value == true)
    }

    @Test
    fun verifyThatSelectedMovieWasSet() = runTest {
        assertEquals(movie, viewModel.selectedMovie.value)
    }

    companion object {
        private val movie = Movie(
            id = 238,
            title = "The Godfather",
            overview = "A chronicle of the fictional Italian-American Corleone crime family",
            popularity = 94.558,
            adult = false,
            voteCount = 16593,
            voteAverage = 8.7,
            language = "en",
            posterPath = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
            originalTitle = "The Godfather",
            releaseDate = "1972-03-14"
        )

        val cast = listOf(
            Cast(
                id = 1,
                name = "Grace Caroline Currey",
                popularity = 8.9,
                character = "Becky",
                path = "/6chZcnjWEiFfpmB6D5BR9YUeIs9.jpg"
            )
        )

        val crew = listOf(
            Crew(
                id = 90812,
                name = "Scott Mann",
                popularity = 7.356,
                job = "Director",
                department = "Directing",
                path = "/8WygpUzfdfztZQqxGE5zn3rCedJ.jpg"
            )
        )

        val credits = Credits(
            id = 1,
            cast = cast,
            crew = crew
        )
    }
}

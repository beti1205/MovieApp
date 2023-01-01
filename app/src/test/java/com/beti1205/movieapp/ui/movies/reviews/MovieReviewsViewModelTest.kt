package com.beti1205.movieapp.ui.movies.reviews

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchmoviereviews.data.MovieReview
import com.beti1205.movieapp.feature.fetchmoviereviews.data.MovieReviewsResult
import com.beti1205.movieapp.feature.fetchmoviereviews.domain.FetchMovieReviewsUseCase
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
class MovieReviewsViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: MovieReviewsViewModel
    private val fetchMovieReviewsUseCase = mockk<FetchMovieReviewsUseCase>()

    @Test
    fun fetchMovieReviews_successful() = runTest {
        coEvery { fetchMovieReviewsUseCase(any()) } returns Result.Success(
            movieReviewResult
        )
        viewModel = MovieReviewsViewModel(
            fetchMovieReviewsUseCase,
            SavedStateHandle(mapOf("movieId" to movieReviewResult.id))
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.reviews.collect() }

        assertEquals(movieReviewResult.results, viewModel.reviews.value)

        collectJob.cancel()
    }

    @Test
    fun fetchMovieReviews_failure() = runTest {
        coEvery { fetchMovieReviewsUseCase(any()) } returns Result.Error(Exception())
        viewModel = MovieReviewsViewModel(
            fetchMovieReviewsUseCase,
            SavedStateHandle(mapOf("movieId" to movieReviewResult.id))
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.reviewsError.collect() }

        assertTrue(viewModel.reviewsError.value)

        collectJob.cancel()
    }

    companion object {
        val movieReviewResult = MovieReviewsResult(
            id = 1,
            results = listOf(
                MovieReview(
                    author = "crastana",
                    content = "The best movie ever...A masterpiece by the young and talented " +
                        "Francis Ford Coppola, about a Mob family and their drama, the story telling" +
                        " is perfect, the acting good, sometimes a little over the top in the case of " +
                        "Thalia Shire (the sister of the director).The 70's were the best" +
                        " years for Hollywood.",
                    id = "62d5ea2fe93e95095cbddefe",
                    createdAt = "2022-07-18T23:18:07.748Z",
                    updatedAt = "2022-07-26T14:21:07.910Z"
                )
            )
        )
    }
}

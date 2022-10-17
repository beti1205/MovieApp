package com.beti1205.movieapp.ui.movies.search

import android.os.Build
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchmovies.domain.SearchMoviesUseCase
import com.beti1205.movieapp.ui.movies.MovieAdapter
import com.beti1205.movieapp.ui.movies.MovieDataProvider
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(sdk = [Build.VERSION_CODES.R])
@OptIn(ExperimentalCoroutinesApi::class)
class SearchMoviesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SearchMoviesViewModel
    private val searchMoviesUseCase = mockk<SearchMoviesUseCase>()

    @Test
    fun fetchSearchedMovies_successful() = runTest {
        coEvery { searchMoviesUseCase(any(), any()) } returns Result.Success(
            MovieDataProvider.apiResponse
        )
        viewModel = SearchMoviesViewModel(
            searchMoviesUseCase,
            SavedStateHandle(mapOf("query" to query))
        )
        val adapter = MovieAdapter(onClick = { _, _ -> })
        val expectedMovieList = MovieDataProvider.apiResponse.items
        val job = launch {
            viewModel.querySearchResults.collectLatest {
                adapter.submitData(it)
            }
        }

        advanceUntilIdle()

        assertEquals(expectedMovieList, adapter.snapshot())

        job.cancel()
    }

    @Test
    fun fetchSearchedMovies_verifyThatErrorOccurred_listIsEmpty() = runTest {
        coEvery { searchMoviesUseCase(any(), any()) } returns Result.Error(Exception())
        viewModel = SearchMoviesViewModel(
            searchMoviesUseCase,
            SavedStateHandle(mapOf("query" to query))
        )
        val adapter = MovieAdapter(onClick = { _, _ -> })
        val job = launch {
            viewModel.querySearchResults.collectLatest {
                adapter.submitData(it)
            }
        }

        advanceUntilIdle()

        assertTrue(adapter.snapshot().isEmpty())

        job.cancel()
    }

    @Test
    fun verifyThatQueryWasSet() = runTest {
        viewModel = SearchMoviesViewModel(
            searchMoviesUseCase,
            SavedStateHandle(mapOf("query" to query))
        )

        assertEquals(query, viewModel.queryFlow.value)
    }

    @Test
    fun verifyThatQueryWasChanged() = runTest {
        viewModel = SearchMoviesViewModel(
            searchMoviesUseCase,
            SavedStateHandle(mapOf("query" to query))
        )

        val newQuery = "Star Wars"
        viewModel.onQueryChanged(newQuery)

        assertEquals(newQuery, viewModel.queryFlow.value)
    }

    companion object {
        private const val query = "The Godfather"
    }
}

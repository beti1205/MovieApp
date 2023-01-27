package com.beti1205.movieapp.ui.movies.search

import androidx.lifecycle.SavedStateHandle
import androidx.paging.AsyncPagingDataDiffer
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.feature.movies.domain.SearchMoviesUseCase
import com.beti1205.movieapp.ui.NoopListCallback
import com.beti1205.movieapp.ui.movies.MovieDataProvider
import com.beti1205.movieapp.ui.movies.MovieDiffCallback
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class SearchMoviesViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    private lateinit var viewModel: SearchMoviesViewModel
    private val searchMoviesUseCase = mockk<SearchMoviesUseCase>()
    private lateinit var differ: AsyncPagingDataDiffer<Movie>

    @Before
    fun setup() {
        differ = AsyncPagingDataDiffer(
            diffCallback = MovieDiffCallback(),
            updateCallback = NoopListCallback(),
            workerDispatcher = Dispatchers.Main
        )
    }

    @Test
    fun fetchSearchedMovies_successful() = runTest {
        coEvery { searchMoviesUseCase(any(), any()) } returns Result.Success(
            MovieDataProvider.apiResponse
        )
        viewModel = SearchMoviesViewModel(
            searchMoviesUseCase,
            SavedStateHandle(mapOf("query" to query))
        )
        val expectedMovieList = MovieDataProvider.apiResponse.items
        val job = launch {
            viewModel.querySearchResults.collectLatest {
                differ.submitData(it)
            }
        }

        advanceUntilIdle()

        assertEquals(expectedMovieList, differ.snapshot().items)

        job.cancel()
    }

    @Test
    fun fetchSearchedMovies_verifyThatErrorOccurred_listIsEmpty() = runTest {
        coEvery { searchMoviesUseCase(any(), any()) } returns Result.Error(Exception())
        viewModel = SearchMoviesViewModel(
            searchMoviesUseCase,
            SavedStateHandle(mapOf("query" to query))
        )
        val job = launch {
            viewModel.querySearchResults.collectLatest {
                differ.submitData(it)
            }
        }

        advanceUntilIdle()

        assertTrue(differ.snapshot().items.isEmpty())

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

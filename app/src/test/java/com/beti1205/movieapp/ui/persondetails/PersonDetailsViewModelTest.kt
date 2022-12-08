package com.beti1205.movieapp.ui.persondetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.fetchpersondetails.domain.FetchPersonDetailsUseCase
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.domain.FetchPersonMovieCreditsUseCase
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class PersonDetailsViewModelTest {
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: PersonDetailsViewModel
    private val fetchPersonDetailsUseCase = mockk<FetchPersonDetailsUseCase>()
    private val fetchPersonMovieCreditsUseCase = mockk<FetchPersonMovieCreditsUseCase>()

    @Test
    fun fetchPersonDetails_successful() = runTest {
        coEvery { fetchPersonDetailsUseCase(any()) } returns Result.Success(
            PersonDetailsDataProvider.personDetails
        )
        coEvery { fetchPersonMovieCreditsUseCase(any()) } returns Result.Success(
            PersonDetailsDataProvider.personMovieCreditsResponse
        )
        viewModel = PersonDetailsViewModel(
            SavedStateHandle(
                mapOf(
                    "selectedPersonId" to PersonDetailsDataProvider.personMovieCreditsResponse.id
                )
            ),
            fetchPersonDetailsUseCase,
            fetchPersonMovieCreditsUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.personDetails.collect() }
        }

        assertEquals(PersonDetailsDataProvider.personDetails, viewModel.personDetails.value)

        collectJob.cancel()
    }

    @Test
    fun fetchPersonDetails_failure() = runTest {
        coEvery { fetchPersonDetailsUseCase(any()) } returns Result.Error(Exception())
        coEvery { fetchPersonMovieCreditsUseCase(any()) } returns Result.Success(
            PersonDetailsDataProvider.personMovieCreditsResponse
        )
        viewModel = PersonDetailsViewModel(
            SavedStateHandle(
                mapOf(
                    "selectedPersonId" to PersonDetailsDataProvider.personMovieCreditsResponse.id
                )
            ),
            fetchPersonDetailsUseCase,
            fetchPersonMovieCreditsUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.personDetails.collect() }
        }

        assertNull(viewModel.personDetails.value)

        collectJob.cancel()
    }

    @Test
    fun fetchPersonMovieCredits_successful() = runTest {
        coEvery { fetchPersonDetailsUseCase(any()) } returns Result.Success(
            PersonDetailsDataProvider.personDetails
        )
        coEvery { fetchPersonMovieCreditsUseCase(any()) } returns Result.Success(
            PersonDetailsDataProvider.personMovieCreditsResponse
        )
        viewModel = PersonDetailsViewModel(
            SavedStateHandle(
                mapOf(
                    "selectedPersonId" to PersonDetailsDataProvider.personMovieCreditsResponse.id
                )
            ),
            fetchPersonDetailsUseCase,
            fetchPersonMovieCreditsUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.sections.collect() }
        }

        assertEquals(PersonDetailsDataProvider.sectionsList, viewModel.sections.value)

        collectJob.cancel()
    }

    @Test
    fun fetchPersonMovieCredits_failure() = runTest {
        coEvery { fetchPersonDetailsUseCase(any()) } returns Result.Success(
            PersonDetailsDataProvider.personDetails
        )
        coEvery { fetchPersonMovieCreditsUseCase(any()) } returns Result.Error(Exception())
        viewModel = PersonDetailsViewModel(
            SavedStateHandle(
                mapOf(
                    "selectedPersonId" to PersonDetailsDataProvider.personMovieCreditsResponse.id
                )
            ),
            fetchPersonDetailsUseCase,
            fetchPersonMovieCreditsUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.sections.collect() }
        }

        assertEquals(PersonDetailsDataProvider.sectionsEmptyCredits, viewModel.sections.value)

        collectJob.cancel()
    }

    @Test
    fun verifyThatSectionExpandedChanged() = runTest {
        coEvery { fetchPersonDetailsUseCase(any()) } returns Result.Success(
            PersonDetailsDataProvider.personDetails
        )
        coEvery { fetchPersonMovieCreditsUseCase(any()) } returns Result.Success(
            PersonDetailsDataProvider.personMovieCreditsResponse
        )
        viewModel = PersonDetailsViewModel(
            SavedStateHandle(
                mapOf(
                    "selectedPersonId" to PersonDetailsDataProvider.personMovieCreditsResponse.id
                )
            ),
            fetchPersonDetailsUseCase,
            fetchPersonMovieCreditsUseCase
        )

        viewModel.onSectionExpandedChanged(
            PersonDetailsDataProvider.sectionsList.first(),
            true
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.sectionStatuses.collect() }
        }

        assertTrue(viewModel.sectionStatuses.value[SectionType.MOVIE_CAST] == true)

        collectJob.cancel()
    }
}

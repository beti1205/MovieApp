/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.persondetails

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.personcredits.domain.FetchPersonCreditsUseCase
import com.beti1205.movieapp.feature.persondetails.domain.FetchPersonDetailsUseCase
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
    private val fetchPersonCreditsUseCase = mockk<FetchPersonCreditsUseCase>()

    @Test
    fun fetchPersonDetails_successful() = runTest {
        coEvery { fetchPersonDetailsUseCase(any()) } returns personDetailsSuccess
        coEvery { fetchPersonCreditsUseCase(any()) } returns personCreditsSuccess
        viewModel = PersonDetailsViewModel(
            SavedStateHandle(selectedPersonId),
            fetchPersonDetailsUseCase,
            fetchPersonCreditsUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.personDetails.collect() }

        assertEquals(PersonDetailsDataProvider.personDetails, viewModel.personDetails.value)

        collectJob.cancel()
    }

    @Test
    fun fetchPersonDetails_failure() = runTest {
        coEvery { fetchPersonDetailsUseCase(any()) } returns Result.Error(Exception())
        coEvery { fetchPersonCreditsUseCase(any()) } returns personCreditsSuccess
        viewModel = PersonDetailsViewModel(
            SavedStateHandle(selectedPersonId),
            fetchPersonDetailsUseCase,
            fetchPersonCreditsUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.personDetails.collect() }
            launch { viewModel.hasError.collect() }
        }

        assertNull(viewModel.personDetails.value)
        assertTrue(viewModel.hasError.value)

        collectJob.cancel()
    }

    @Test
    fun fetchPersonSectionsItems_successful() = runTest {
        coEvery { fetchPersonDetailsUseCase(any()) } returns personDetailsSuccess
        coEvery { fetchPersonCreditsUseCase(any()) } returns personCreditsSuccess
        viewModel = PersonDetailsViewModel(
            SavedStateHandle(selectedPersonId),
            fetchPersonDetailsUseCase,
            fetchPersonCreditsUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.movieCastSection.collect() }
            launch { viewModel.movieCrewSection.collect() }
            launch { viewModel.tvCastSection.collect() }
            launch { viewModel.tvCrewSection.collect() }
        }

        assertEquals(PersonDetailsDataProvider.sectionMovieCast, viewModel.movieCastSection.value)
        assertEquals(PersonDetailsDataProvider.sectionMovieCrew, viewModel.movieCrewSection.value)
        assertEquals(PersonDetailsDataProvider.sectionTVCast, viewModel.tvCastSection.value)
        assertEquals(PersonDetailsDataProvider.sectionTVCrew, viewModel.tvCrewSection.value)

        collectJob.cancel()
    }

    @Test
    fun fetchPersonSectionsItems_failure() = runTest {
        coEvery { fetchPersonDetailsUseCase(any()) } returns personDetailsSuccess
        coEvery { fetchPersonCreditsUseCase(any()) } returns Result.Error(Exception())
        viewModel = PersonDetailsViewModel(
            SavedStateHandle(selectedPersonId),
            fetchPersonDetailsUseCase,
            fetchPersonCreditsUseCase
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.hasCreditsError.collect() }
            launch { viewModel.movieCastSection.collect() }
            launch { viewModel.movieCrewSection.collect() }
            launch { viewModel.tvCastSection.collect() }
            launch { viewModel.tvCrewSection.collect() }
        }

        assertEquals(
            PersonDetailsDataProvider.sectionEmptyMovieCast,
            viewModel.movieCastSection.value
        )
        assertEquals(
            PersonDetailsDataProvider.sectionEmptyMovieCrew,
            viewModel.movieCrewSection.value
        )
        assertEquals(PersonDetailsDataProvider.sectionEmptyTVCast, viewModel.tvCastSection.value)
        assertEquals(PersonDetailsDataProvider.sectionEmptyTVCrew, viewModel.tvCrewSection.value)

        collectJob.cancel()
    }

    @Test
    fun verifyThatSectionExpandedChanged() = runTest {
        coEvery { fetchPersonDetailsUseCase(any()) } returns personDetailsSuccess
        coEvery { fetchPersonCreditsUseCase(any()) } returns personCreditsSuccess
        viewModel = PersonDetailsViewModel(
            SavedStateHandle(selectedPersonId),
            fetchPersonDetailsUseCase,
            fetchPersonCreditsUseCase
        )

        viewModel.onSectionExpandedChanged(
            SectionType.MOVIE_CAST,
            true
        )

        val collectJob = launch(UnconfinedTestDispatcher()) { viewModel.sectionStatuses.collect() }

        assertTrue(viewModel.sectionStatuses.value[SectionType.MOVIE_CAST] == true)

        collectJob.cancel()
    }

    companion object {
        val personCreditsSuccess = Result.Success(
            PersonDetailsDataProvider.personCredits
        )
        val personDetailsSuccess = Result.Success(
            PersonDetailsDataProvider.personDetails
        )
        val selectedPersonId = mapOf(
            "selectedPersonId" to PersonDetailsDataProvider.personMovieCreditsResponse.id
        )
    }
}

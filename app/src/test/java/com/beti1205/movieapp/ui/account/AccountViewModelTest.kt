package com.beti1205.movieapp.ui.account

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.createsession.data.SessionResponse
import com.beti1205.movieapp.feature.createsession.domain.CreateSessionUseCase
import com.beti1205.movieapp.feature.fetchrequesttoken.data.RequestTokenResponse
import com.beti1205.movieapp.feature.fetchrequesttoken.domain.FetchRequestTokenUseCase
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.launch
import kotlinx.coroutines.test.UnconfinedTestDispatcher
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertNull
import org.junit.Assert.assertTrue
import org.junit.Rule
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class AccountViewModelTest {

    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @get:Rule
    val rule = InstantTaskExecutorRule()

    private lateinit var viewModel: AccountViewModel
    private val fetchRequestTokenUseCase = mockk<FetchRequestTokenUseCase>()
    private val createSessionUseCase = mockk<CreateSessionUseCase>()
    private val authManager = mockk<AuthManager>()

    @Test
    fun getRequestToken_successful() = runTest {
        coEvery { fetchRequestTokenUseCase() } returns getTokenSuccess
        val slot = slot<String>()
        every { authManager.setRequestToken(requestToken = capture(slot)) } returns Unit
        coEvery { authManager.requestTokenFlow } returns flowOf("requestToken")
        every { authManager.isLoggedIn } returns flowOf(false)
        every { authManager.requestToken } returns "requestToken"

        viewModel = AccountViewModel(
            SavedStateHandle(),
            fetchRequestTokenUseCase,
            createSessionUseCase,
            authManager
        )

        viewModel.getRequestToken()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.hasError.collect()
        }

        assertFalse(viewModel.hasError.value)
        assertEquals("requestToken", slot.captured)

        collectJob.cancel()
    }

    @Test
    fun getRequestToken_failed() = runTest {
        coEvery { fetchRequestTokenUseCase() } returns Result.Error(Exception())
        every { authManager.setRequestToken(any()) } returns Unit
        coEvery { authManager.requestTokenFlow } returns flowOf(null)
        every { authManager.isLoggedIn } returns flowOf(false)
        every { authManager.requestToken } returns null

        viewModel = AccountViewModel(
            SavedStateHandle(),
            fetchRequestTokenUseCase,
            createSessionUseCase,
            authManager
        )

        viewModel.getRequestToken()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.hasError.collect()
        }

        assertTrue(viewModel.hasError.value)

        collectJob.cancel()
    }

    @Test
    fun createSession_successful() = runTest {
        coEvery { createSessionUseCase(any()) } returns createSessionSuccess
        val tokens = mutableListOf<String?>()
        every { authManager.setRequestToken(requestToken = captureNullable(tokens)) } returns Unit
        val sessionIdSlot = slot<String>()
        every { authManager.setSessionId(sessionId = capture(sessionIdSlot)) } returns Unit
        coEvery { authManager.requestTokenFlow } returns flowOf("requestToken")
        every { authManager.isLoggedIn } returns flowOf(true)
        every { authManager.requestToken } returns "requestToken"

        viewModel = AccountViewModel(
            SavedStateHandle(mapOf("authenticationSuccess" to true)),
            fetchRequestTokenUseCase,
            createSessionUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch {
                viewModel.hasError.collect()
            }
            launch {
                viewModel.isLoggedIn.collect()
            }
        }

        assertTrue(!viewModel.hasError.value)
        assertTrue(tokens.size == 1)
        assertNull(tokens.first())
        assertEquals("sessionId", sessionIdSlot.captured)
        assertTrue(viewModel.isLoggedIn.value)

        collectJob.cancel()
    }

    @Test
    fun createSession_failed() = runTest {
        coEvery { createSessionUseCase(any()) } returns Result.Error(Exception())
        every { authManager.setRequestToken(any()) } returns Unit
        every { authManager.setSessionId(any()) } returns Unit
        coEvery { authManager.requestTokenFlow } returns flowOf("requestToken")
        every { authManager.isLoggedIn } returns flowOf(false)
        every { authManager.requestToken } returns "requestToken"

        viewModel = AccountViewModel(
            SavedStateHandle(mapOf("authenticationSuccess" to true)),
            fetchRequestTokenUseCase,
            createSessionUseCase,
            authManager
        )

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch {
                viewModel.hasError.collect()
            }
            launch {
                viewModel.isLoggedIn.collect()
            }
        }

        assertTrue(viewModel.hasError.value)
        assertFalse(viewModel.isLoggedIn.value)

        collectJob.cancel()
    }

    companion object {
        val getTokenSuccess = Result.Success(
            RequestTokenResponse(
                success = true,
                expiresAt = "19:03:58",
                requestToken = "requestToken"
            )
        )
        val createSessionSuccess = Result.Success(
            SessionResponse(
                success = true,
                sessionId = "sessionId"
            )
        )
    }
}

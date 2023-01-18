package com.beti1205.movieapp.ui.account

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.lifecycle.SavedStateHandle
import com.beti1205.movieapp.MainDispatcherRule
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.createsession.data.SessionResponse
import com.beti1205.movieapp.feature.createsession.domain.CreateSessionUseCase
import com.beti1205.movieapp.feature.deletesession.domain.DeleteSessionUseCase
import com.beti1205.movieapp.feature.fetchaccountdetails.data.AccountDetails
import com.beti1205.movieapp.feature.fetchaccountdetails.data.Avatar
import com.beti1205.movieapp.feature.fetchaccountdetails.data.Tmdb
import com.beti1205.movieapp.feature.fetchaccountdetails.domain.FetchAccountDetailsUseCase
import com.beti1205.movieapp.feature.fetchrequesttoken.data.RequestTokenResponse
import com.beti1205.movieapp.feature.fetchrequesttoken.domain.FetchRequestTokenUseCase
import io.mockk.coEvery
import io.mockk.coVerify
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
    private val deleteSessionUseCase = mockk<DeleteSessionUseCase>()
    private val fetchAccountDetailsUseCase = mockk<FetchAccountDetailsUseCase>()

    @Test
    fun getRequestToken_successful() = runTest {
        coEvery { fetchRequestTokenUseCase() } returns getTokenSuccess
        every { authManager.isLoggedIn } returns flowOf(false)
        val state = SavedStateHandle(mapOf("request_token" to null))

        viewModel = AccountViewModel(
            state,
            fetchRequestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            authManager
        )

        viewModel.getRequestToken()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.hasError.collect()
        }

        assertFalse(viewModel.hasError.value)
        assertEquals("requestToken", state["request_token"])

        collectJob.cancel()
    }

    @Test
    fun getRequestToken_failed() = runTest {
        coEvery { fetchRequestTokenUseCase() } returns Result.Error(Exception())
        every { authManager.isLoggedIn } returns flowOf(false)
        val state = SavedStateHandle(mapOf("request_token" to null))

        viewModel = AccountViewModel(
            state,
            fetchRequestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            authManager
        )

        viewModel.getRequestToken()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.hasError.collect()
        }

        assertTrue(viewModel.hasError.value)
        assertNull(state["request_token"])

        collectJob.cancel()
    }

    @Test
    fun createSession_successful() = runTest {
        coEvery { createSessionUseCase(any()) } returns createSessionSuccess
        val sessionIdSlot = slot<String>()
        every { authManager.setSessionId(sessionId = capture(sessionIdSlot)) } returns Unit
        every { authManager.isLoggedIn } returns flowOf(true)
        val state = SavedStateHandle(mapOf("approved" to true, "request_token" to "requestToken"))

        viewModel = AccountViewModel(
            state,
            fetchRequestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
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
        assertEquals("sessionId", sessionIdSlot.captured)
        assertTrue(viewModel.isLoggedIn.value)

        collectJob.cancel()
    }

    @Test
    fun createSession_failed() = runTest {
        coEvery { createSessionUseCase(any()) } returns Result.Error(Exception())
        every { authManager.isLoggedIn } returns flowOf(false)
        val state = SavedStateHandle(mapOf("approved" to true, "request_token" to "requestToken"))

        viewModel = AccountViewModel(
            state,
            fetchRequestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
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

    @Test
    fun deleteSession_successful() = runTest {
        coEvery { deleteSessionUseCase() } returns Result.Success(Unit)
        every { authManager.isLoggedIn } returns flowOf(false)
        val state = SavedStateHandle(mapOf("request_token" to "requestToken"))

        viewModel = AccountViewModel(
            state,
            fetchRequestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            authManager
        )

        viewModel.deleteSession()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.isLoggedIn.collect()
        }

        coVerify { deleteSessionUseCase() }
        assertFalse(viewModel.isLoggedIn.value)

        collectJob.cancel()
    }

    @Test
    fun deleteSession_failed() = runTest {
        coEvery { deleteSessionUseCase() } returns Result.Error(Exception())
        every { authManager.isLoggedIn } returns flowOf(true)
        val state = SavedStateHandle(mapOf("request_token" to null))

        viewModel = AccountViewModel(
            state,
            fetchRequestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            authManager
        )

        viewModel.deleteSession()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            launch { viewModel.hasError.collect() }
            launch { viewModel.isLoggedIn.collect() }
        }

        coVerify { deleteSessionUseCase() }
        assertTrue(viewModel.isLoggedIn.value)
        assertTrue(viewModel.hasError.value)

        collectJob.cancel()
    }

    @Test
    fun getAccountDetails_success() = runTest {
        coEvery { fetchAccountDetailsUseCase() } returns accountDetails
        every { authManager.isLoggedIn } returns flowOf(true)
        val state = SavedStateHandle()

        viewModel = AccountViewModel(
            state,
            fetchRequestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            authManager
        )

        viewModel.getAccountDetails()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.account.collect()
        }

        assertEquals(accountDetails.data, viewModel.account.value)

        collectJob.cancel()
    }

    @Test
    fun getAccountDetails_failed() = runTest {
        coEvery { fetchAccountDetailsUseCase() } returns Result.Error(Exception())
        every { authManager.isLoggedIn } returns flowOf(true)
        val state = SavedStateHandle()

        viewModel = AccountViewModel(
            state,
            fetchRequestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            authManager
        )

        viewModel.getAccountDetails()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.account.collect()
        }

        assertNull(viewModel.account.value)

        collectJob.cancel()
    }

    @Test
    fun onErrorHandled_verifyThatFalseWasSet() = runTest {
        coEvery { fetchRequestTokenUseCase() } returns Result.Error(Exception())
        every { authManager.isLoggedIn } returns flowOf(false)
        val state = SavedStateHandle(mapOf("request_token" to null))

        viewModel = AccountViewModel(
            state,
            fetchRequestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            authManager
        )

        viewModel.getRequestToken()

        val collectJob = launch(UnconfinedTestDispatcher()) {
            viewModel.hasError.collect()
        }

        assertTrue(viewModel.hasError.value)

        viewModel.onErrorHandled()

        assertFalse(viewModel.hasError.value)

        collectJob.cancel()
    }

    @Test
    fun onDeniedHandled_verifyThatFalseWasSet() = runTest {
        val state = SavedStateHandle(mapOf("denied" to true, "request_token" to null))
        every { authManager.isLoggedIn } returns flowOf(false)

        viewModel = AccountViewModel(
            state,
            fetchRequestTokenUseCase,
            createSessionUseCase,
            deleteSessionUseCase,
            fetchAccountDetailsUseCase,
            authManager
        )

        viewModel.onDeniedHandled()

        assertFalse(state["denied"]!!)
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
        val accountDetails = Result.Success(
            AccountDetails(
                id = 1,
                name = "Beata",
                username = "beti1205",
                avatar = Avatar(tmdb = Tmdb(avatarPath = null))
            )
        )
    }
}

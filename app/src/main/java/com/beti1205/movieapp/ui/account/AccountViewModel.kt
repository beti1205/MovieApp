package com.beti1205.movieapp.ui.account

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.createsession.domain.CreateSessionUseCase
import com.beti1205.movieapp.feature.fetchrequesttoken.domain.FetchRequestTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val REQUEST_TOKEN = "request_token"
private const val APPROVED = "approved"
private const val DENIED = "denied"

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val stateHandle: SavedStateHandle,
    private val fetchRequestTokenUseCase: FetchRequestTokenUseCase,
    private val createSessionUseCase: CreateSessionUseCase,
    private val authManager: AuthManager
) : ViewModel() {

    private val approved = stateHandle.getStateFlow(APPROVED, false)

    val denied = stateHandle.getStateFlow(DENIED, false)

    private val _hasError = MutableStateFlow(false)

    val hasError: StateFlow<Boolean> = _hasError.asStateFlow()

    private val _requestToken = stateHandle.getStateFlow<String?>(REQUEST_TOKEN, null)

    val authUri = _requestToken
        .filterNotNull()
        .map { token ->
            buildAuthUri(token)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            null
        )

    val isLoggedIn = authManager.isLoggedIn.stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000L),
        false
    )

    init {
        viewModelScope.launch {
            approved.collect { isSuccess ->
                val token = _requestToken.value
                stateHandle[REQUEST_TOKEN] = null
                stateHandle[APPROVED] = false

                if (!isSuccess) {
                    return@collect
                }

                if (token != null) {
                    createSession(token)
                } else {
                    _hasError.value = true
                }
            }
        }
    }

    fun getRequestToken() {
        viewModelScope.launch {
            val result = fetchRequestTokenUseCase()

            when (result) {
                is Result.Success ->
                    if (result.data.success) {
                        stateHandle[REQUEST_TOKEN] = result.data.requestToken
                    } else {
                        _hasError.value = true
                    }
                is Result.Error -> _hasError.value = true
            }
        }
    }

    private fun createSession(token: String) {
        viewModelScope.launch {
            val result = createSessionUseCase(token)

            when (result) {
                is Result.Success -> if (result.data.success) {
                    authManager.setSessionId(result.data.sessionId)
                } else {
                    _hasError.value = true
                }
                is Result.Error -> _hasError.value = true
            }
        }
    }

    fun onErrorHandled() {
        _hasError.value = false
    }

    fun onDeniedHandled() {
        stateHandle[DENIED] = false
    }

    private fun buildAuthUri(token: String?): Uri? {
        val deepLinkUri = Uri.Builder().apply {
            scheme("movieapp")
            authority("app")
            appendPath("authenticate")
        }.build()

        return Uri.Builder().apply {
            scheme("https")
            authority("www.themoviedb.org")
            appendPath("authenticate")
            appendPath(token)
            appendQueryParameter("redirect_to", deepLinkUri.toString())
        }.build()
    }
}

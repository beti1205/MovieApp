package com.beti1205.movieapp.ui.account

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
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    state: SavedStateHandle,
    private val fetchRequestTokenUseCase: FetchRequestTokenUseCase,
    private val createSessionUseCase: CreateSessionUseCase,
    private val authManager: AuthManager
) : ViewModel() {

    private val authenticationSuccess = state.getStateFlow("authenticationSuccess", false)

    private val _hasError = MutableStateFlow<Boolean>(false)
    val hasError: StateFlow<Boolean> = _hasError.asStateFlow()

    val requestToken = authManager.requestTokenFlow.stateIn(
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
            authenticationSuccess.collect { isSuccess ->
                if (!isSuccess) {
                    return@collect
                }

                val token = authManager.requestToken
                authManager.setRequestToken(null)

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
                        authManager.setRequestToken(result.data.requestToken)
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
}

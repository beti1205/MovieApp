package com.beti1205.movieapp.ui.account

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.createsession.domain.CreateSessionUseCase
import com.beti1205.movieapp.feature.fetchrequesttoken.domain.FetchRequestTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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

    private val _requestToken = MutableStateFlow<String?>(null)
    val requestToken: StateFlow<String?> = _requestToken.asStateFlow()

    fun getRequestToken() {
        viewModelScope.launch {
            val result = fetchRequestTokenUseCase()

            when (result) {
                is Result.Success ->
                    if (result.data.success) {
                        _requestToken.value = result.data.requestToken
                    } else {
                        TODO()
                    }
                is Result.Error -> {}
            }
        }
    }

    private fun createSession(token: String) {
        viewModelScope.launch {
            val result = createSessionUseCase(token)

            when (result) {
                is Result.Success -> if (result.data.success) {
                    authManager.sessionId = result.data.sessionId
                } else {
                    TODO()
                }
                is Result.Error -> {}
            }
        }
    }
}

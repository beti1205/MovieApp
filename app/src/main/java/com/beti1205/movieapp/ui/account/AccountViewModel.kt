package com.beti1205.movieapp.ui.account

import android.net.Uri
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails
import com.beti1205.movieapp.feature.accountdetails.domain.FetchAccountDetailsUseCase
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.feature.movies.domain.FetchFavoriteMoviesUseCase
import com.beti1205.movieapp.feature.session.domain.CreateSessionUseCase
import com.beti1205.movieapp.feature.session.domain.DeleteSessionUseCase
import com.beti1205.movieapp.feature.token.domain.RequestTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.distinctUntilChanged
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
    private val requestTokenUseCase: RequestTokenUseCase,
    private val createSessionUseCase: CreateSessionUseCase,
    private val deleteSessionUseCase: DeleteSessionUseCase,
    private val fetchAccountDetailsUseCase: FetchAccountDetailsUseCase,
    private val fetchFavoriteMoviesUseCase: FetchFavoriteMoviesUseCase,
    private val authManager: AuthManager
) : ViewModel() {

    private val approved = stateHandle.getStateFlow(APPROVED, false)

    val denied = stateHandle.getStateFlow(DENIED, false)

    private val _requestToken = stateHandle.getStateFlow<String?>(REQUEST_TOKEN, null)

    private val _hasError = MutableStateFlow(false)
    val hasError: StateFlow<Boolean> = _hasError.asStateFlow()

    private val _account = MutableStateFlow<AccountDetails?>(null)
    val account: StateFlow<AccountDetails?> = _account.asStateFlow()

    private val _movies = MutableStateFlow<List<Movie>>(emptyList())
    val movies: StateFlow<List<Movie>> = _movies.asStateFlow()

    val authUri = _requestToken
        .filterNotNull()
        .map { token ->
            buildAuthUri(token)
        }.stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            null
        )

    val isLoggedIn = authManager.isLoggedInFlow.stateIn(
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

        viewModelScope.launch {
            authManager.isLoggedInFlow
                .distinctUntilChanged()
                .collect { isLoggedIn ->
                    if (isLoggedIn) {
                        getAccountDetails()
                        getFavoriteMovies()
                    }
                }
        }
    }

    fun getRequestToken() {
        viewModelScope.launch {
            val result = requestTokenUseCase()

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

    fun deleteSession() {
        viewModelScope.launch {
            val result = deleteSessionUseCase()

            if (result is Result.Error) {
                _hasError.value = true
            }
        }
    }

    private fun getAccountDetails() {
        viewModelScope.launch {
            val result = fetchAccountDetailsUseCase()

            when (result) {
                is Result.Success -> _account.value = result.data
                is Result.Error -> {}
            }
        }
    }

    private fun getFavoriteMovies() {
        viewModelScope.launch {
            val result = fetchFavoriteMoviesUseCase()

            when (result) {
                is Result.Success -> _movies.value = result.data.items
                is Result.Error -> {}
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

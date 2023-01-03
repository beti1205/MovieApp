package com.beti1205.movieapp.ui.account

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.createrequesttoken.domain.CreateRequestTokenUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AccountViewModel @Inject constructor(
    private val createRequestTokenUseCase: CreateRequestTokenUseCase
) : ViewModel() {

    private val _requestToken = MutableStateFlow<String?>(null)
    val requestToken: StateFlow<String?> = _requestToken.asStateFlow()

    fun createRequestToken() {
        viewModelScope.launch {
            val result = createRequestTokenUseCase()

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
}

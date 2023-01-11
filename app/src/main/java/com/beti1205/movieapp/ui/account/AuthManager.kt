package com.beti1205.movieapp.ui.account

import com.fredporciuncula.flow.preferences.FlowSharedPreferences
import com.fredporciuncula.flow.preferences.Preference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

interface AuthManager {
    val sessionId: String?
    val isLoggedIn: Flow<Boolean>

    fun setSessionId(sessionId: String?)
}

class AuthManagerImpl @Inject constructor(
    @Named("encryptedFlowSharedPreferences") sharedPreferences: FlowSharedPreferences
) : AuthManager {

    private val sessionIdPreference: Preference<String?> = sharedPreferences.getNullableString(
        key = "sessionId",
        defaultValue = null
    )

    override val sessionId: String?
        get() = sessionIdPreference.get()

    override val isLoggedIn: Flow<Boolean> = sessionIdPreference.asFlow().map { it != null }

    override fun setSessionId(sessionId: String?) = sessionIdPreference.set(sessionId)
}

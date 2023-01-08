package com.beti1205.movieapp.ui.account

import com.fredporciuncula.flow.preferences.FlowSharedPreferences
import com.fredporciuncula.flow.preferences.Preference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

interface AuthManager {
    val requestToken: String?
    val requestTokenFlow: Flow<String?>
    val sessionId: String?
    val isLoggedIn: Flow<Boolean>

    fun setRequestToken(requestToken: String?)
    fun setSessionId(sessionId: String?)
}

class AuthManagerImpl @Inject constructor(
    @Named("encryptedFlowSharedPreferences") sharedPreferences: FlowSharedPreferences
) : AuthManager {
    private val requestTokenPreference: Preference<String?> = sharedPreferences.getNullableString(
        key = "requestToken",
        defaultValue = null
    )
    override val requestToken: String?
        get() = requestTokenPreference.get()

    override val requestTokenFlow: Flow<String?> = requestTokenPreference.asFlow()

    private val sessionIdPreference: Preference<String?> = sharedPreferences.getNullableString(
        key = "sessionId",
        defaultValue = null
    )

    override val sessionId: String?
        get() = sessionIdPreference.get()

    override val isLoggedIn: Flow<Boolean> = sessionIdPreference.asFlow().map { it != null }

    override fun setRequestToken(requestToken: String?) = requestTokenPreference.set(requestToken)

    override fun setSessionId(sessionId: String?) = sessionIdPreference.set(sessionId)
}

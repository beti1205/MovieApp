package com.beti1205.movieapp.common

import com.fredporciuncula.flow.preferences.FlowSharedPreferences
import com.fredporciuncula.flow.preferences.Preference
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Named

interface AuthManager {
    val sessionId: String?
    val isLoggedInFlow: Flow<Boolean>
    val isLoggedIn: Boolean
    val accountId: Int

    fun setSessionId(sessionId: String?)
    fun setAccountId(accountId: Int)
}

class AuthManagerImpl @Inject constructor(
    @Named("encryptedFlowSharedPreferences") sharedPreferences: FlowSharedPreferences
) : AuthManager {

    private val sessionIdPreference: Preference<String?> = sharedPreferences.getNullableString(
        key = "sessionId",
        defaultValue = null
    )

    private val accountIdPreference: Preference<Int> = sharedPreferences.getInt(
        key = "accountId",
        defaultValue = 0
    )

    override val sessionId: String?
        get() = sessionIdPreference.get()

    override val isLoggedInFlow: Flow<Boolean> = sessionIdPreference.asFlow().map { it != null }

    override val isLoggedIn: Boolean
        get() = sessionIdPreference.get() != null

    override val accountId: Int
        get() = accountIdPreference.get()

    override fun setSessionId(sessionId: String?) = sessionIdPreference.set(sessionId)
    override fun setAccountId(accountId: Int) = accountIdPreference.set(accountId)
}

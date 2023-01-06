package com.beti1205.movieapp.ui.account

import android.content.SharedPreferences
import com.beti1205.movieapp.utils.StringPreference
import javax.inject.Inject
import javax.inject.Named

class AuthManager @Inject constructor(
    @Named("encryptedSharedPreferences") sharedPreferences: SharedPreferences
) {
    var sessionId: String? by StringPreference(sharedPreferences, "SESSION_KEY")

    fun isLoggedIn(): Boolean = sessionId != null
}

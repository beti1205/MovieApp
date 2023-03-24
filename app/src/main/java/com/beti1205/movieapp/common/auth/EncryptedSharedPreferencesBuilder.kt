/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.common.auth

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import java.io.File
import java.security.GeneralSecurityException
import java.security.KeyStore

private const val KEYSTORE_PROVIDER = "AndroidKeyStore"
private const val SHARED_PREFS_FILENAME = "encrypted_preferences"

// https://stackoverflow.com/a/71569624
// Avoid EncryptedSharedPreferences crashes
class EncryptedSharedPreferencesBuilder(
    private val context: Context,
    private val masterKey: MasterKey
) {

    fun build(): SharedPreferences {
        return try {
            createSharedPreferences()
        } catch (gsException: GeneralSecurityException) {
            Log.d(
                "EncryptedSharedPref",
                "Error occurred while create shared pref=$gsException"
            )
            // There's not much point in keeping data you can't decrypt anymore,
            // delete & re-create; user has to start from scratch
            deleteSharedPreferences()
            createSharedPreferences()
        }
    }

    private fun createSharedPreferences() = EncryptedSharedPreferences.create(
        context,
        SHARED_PREFS_FILENAME,
        masterKey,
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    // Clearing getSharedPreferences using default Preference wrapper.
    // This is to work around any key-mismatches that may happen.
    private fun clearSharedPreference() {
        context.getSharedPreferences(SHARED_PREFS_FILENAME, Context.MODE_PRIVATE).edit().clear()
            .apply()
    }

    // Workaround [https://github.com/google/tink/issues/535#issuecomment-912170221]
    // Issue Tracker - https://issuetracker.google.com/issues/176215143?pli=1
    private fun deleteSharedPreferences() {
        try {
            val sharedPrefsFile = File(
                "${context.filesDir.parent}/shared_prefs/$SHARED_PREFS_FILENAME.xml"
            )

            // Clear the encrypted prefs
            clearSharedPreference()

            // Delete the encrypted prefs file
            if (sharedPrefsFile.exists()) {
                val deleted = sharedPrefsFile.delete()
                Log.d(
                    "EncryptedSharedPref",
                    "Shared pref file deleted=$deleted; path=${sharedPrefsFile.absolutePath}"
                )
            } else {
                Log.d(
                    "EncryptedSharedPref",
                    "Shared pref file non-existent; path=${sharedPrefsFile.absolutePath}"
                )
            }

            // Delete the master key
            val keyStore = KeyStore.getInstance(KEYSTORE_PROVIDER)
            keyStore.load(null)
            keyStore.deleteEntry(MasterKey.DEFAULT_MASTER_KEY_ALIAS)
        } catch (e: Exception) {
            Log.d(
                "EncryptedSharedPref",
                "Error occurred while trying to reset shared pref=$e"
            )
        }
    }
}

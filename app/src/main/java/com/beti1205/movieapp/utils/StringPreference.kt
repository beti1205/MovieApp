package com.beti1205.movieapp.utils

import android.content.SharedPreferences
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

class StringPreference(
    private val sharedPreferences: SharedPreferences,
    private val key: String,
    private val defaultValue: String? = null
) : ReadWriteProperty<Any, String?> {

    override fun getValue(thisRef: Any, property: KProperty<*>): String? =
        sharedPreferences.getString(key, defaultValue) ?: defaultValue

    override fun setValue(thisRef: Any, property: KProperty<*>, value: String?) =
        sharedPreferences.edit()
            .putString(key, value)
            .apply()
}

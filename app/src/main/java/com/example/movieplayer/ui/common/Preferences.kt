package com.example.movieplayer.ui.common

import android.content.SharedPreferences
import com.example.movieplayer.utils.IntPreference
import javax.inject.Inject

class Preferences @Inject constructor(
    sharedPreferences: SharedPreferences
) {
    var movieOrder: Int by IntPreference(sharedPreferences, MOVIE_ORDER_KEY)
    var tvOrder: Int by IntPreference(sharedPreferences, TV_ORDER_KEY)

    companion object {
        const val MOVIE_ORDER_KEY = "MOVIE_ORDER_KEY"
        const val TV_ORDER_KEY = "TV_ORDER_KEY"
    }
}

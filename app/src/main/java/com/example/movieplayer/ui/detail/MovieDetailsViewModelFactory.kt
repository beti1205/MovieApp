package com.example.movieplayer.ui.detail

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.movieplayer.domain.Movie

class MovieDetailsViewModelFactory(private val movie: Movie, private val app: Application) : ViewModelProvider.Factory {
    @Suppress("unchecked_cast")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MovieDetailsViewModel::class.java)) {
            return MovieDetailsViewModel(movie, app) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

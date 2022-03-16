package com.example.movieplayer.ui.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.movieplayer.domain.Movie

class MovieDetailsViewModel(movie: Movie, app: Application):AndroidViewModel(app) {

    private val _selectedMovie = MutableLiveData<Movie>()
    val selectedMovie: LiveData<Movie>
        get() = _selectedMovie

    init {
        _selectedMovie.value = movie
    }
}
package com.example.movieplayer.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.movieplayer.domain.Movie
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieDetailsViewModel @Inject constructor(
    private val state: SavedStateHandle
) : ViewModel() {

    private val _selectedMovie = state.getLiveData<Movie>("selectedMovie")
    val selectedMovie: LiveData<Movie>
        get() = _selectedMovie

//    init {
//        _selectedMovie.value = state.get<Movie>("selectedMovie")
//    }

}
package com.beti1205.movieapp.ui.movies.details

import com.beti1205.movieapp.feature.fetchcredits.data.Credits
import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetails

data class MovieDetailsScreenState(
    val movieDetails: MovieDetails? = null,
    val credits: Credits? = null,
    val hasError: Boolean = false
)

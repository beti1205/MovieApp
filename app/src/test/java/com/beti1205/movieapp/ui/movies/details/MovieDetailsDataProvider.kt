package com.beti1205.movieapp.ui.movies.details

import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.feature.fetchcredits.data.Credits
import com.beti1205.movieapp.feature.fetchcredits.data.Crew
import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetails

object MovieDetailsDataProvider {

    private val genresList = listOf(
        Genre(
            id = 18,
            name = "Drama"
        )
    )

    val movieDetails = MovieDetails(
        id = 238,
        title = "The Godfather",
        overview = "A chronicle of the fictional Italian-American Corleone crime family",
        voteAverage = 8.7,
        posterPath = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
        releaseDate = "1972-03-14",
        genres = genresList
    )

    private val cast = listOf(
        Cast(
            id = 1,
            name = "Grace Caroline Currey",
            popularity = 8.9,
            character = "Becky",
            path = "/6chZcnjWEiFfpmB6D5BR9YUeIs9.jpg"
        )
    )

    private val crew = listOf(
        Crew(
            id = 90812,
            name = "Scott Mann",
            popularity = 7.356,
            job = "Director",
            department = "Directing",
            path = "/8WygpUzfdfztZQqxGE5zn3rCedJ.jpg"
        )
    )

    val credits = Credits(
        id = 1,
        cast = cast,
        crew = crew
    )

    val movieDetailsScreenState = MovieDetailsScreenState(
        movieDetails = movieDetails,
        credits = credits,
        hasError = false
    )
}

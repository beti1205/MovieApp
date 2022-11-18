package com.beti1205.movieapp.ui.movies.details

import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.feature.fetchcredits.data.Credits
import com.beti1205.movieapp.feature.fetchcredits.data.Crew
import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetails
import com.beti1205.movieapp.feature.fetchmovies.data.Movie

object MovieDetailsDataProvider {

    val genresList = listOf(
        Genre(
            id = 18,
            name = "Drama"
        )
    )

    val movieDetails = MovieDetails(genresList)

    val movie = Movie(
        id = 238,
        title = "The Godfather",
        overview = "A chronicle of the fictional Italian-American Corleone crime family",
        popularity = 94.558,
        adult = false,
        voteCount = 16593,
        voteAverage = 8.7,
        language = "en",
        posterPath = "/3bhkrj58Vtu7enYsRolD1fZdja1.jpg",
        originalTitle = "The Godfather",
        releaseDate = "1972-03-14"
    )

    val cast = listOf(
        Cast(
            id = 1,
            name = "Grace Caroline Currey",
            popularity = 8.9,
            character = "Becky",
            path = "/6chZcnjWEiFfpmB6D5BR9YUeIs9.jpg"
        )
    )

    val crew = listOf(
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
}

package com.beti1205.movieapp.ui.movies.details

import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.feature.fetchcredits.data.Credits
import com.beti1205.movieapp.feature.fetchcredits.data.Crew
import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetails

object MovieDetailsPreviewDataProvider {
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

    val genres = listOf(
        Genre(
            id = 18,
            name = "Drama"
        ),
        Genre(
            id = 20,
            name = "Crime"
        )
    )

    val movie = MovieDetails(
        id = 985939,
        title = "The Godfather II",
        overview = "For best friends Becky and Hunter, life is all about conquering fears and pushing limits.",
        voteAverage = 7.4,
        posterPath = "/spCAxD99U1A6jsiePFoqdEcY0dG.jpg",
        releaseDate = "2022-08-11",
        genres = genres
    )
}

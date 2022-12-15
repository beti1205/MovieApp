package com.beti1205.movieapp.ui.movies.details

import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetails

object MovieDetailsPreviewDataProvider {

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

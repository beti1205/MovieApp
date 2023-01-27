package com.beti1205.movieapp.ui.movies.details.widget

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.feature.credits.data.Cast
import com.beti1205.movieapp.feature.credits.data.Credits
import com.beti1205.movieapp.feature.credits.data.Crew
import com.beti1205.movieapp.feature.moviedetails.data.MovieDetails

class MovieDetailsPreviewProvider : PreviewParameterProvider<Pair<MovieDetails, Credits>> {
    override val values = sequenceOf(
        MovieDetails(
            id = 985939,
            title = "The Godfather II",
            overview = "In the continuing saga of the Corleone crime family, a young " +
                "Vito Corleone grows up in Sicily and in 1910s New York.",
            voteAverage = 8.602,
            posterPath = "/spCAxD99U1A6jsiePFoqdEcY0dG.jpg",
            releaseDate = "1974-12-20",
            genres = listOf(
                Genre(
                    id = 18,
                    name = "Drama"
                ),
                Genre(
                    id = 20,
                    name = "Crime"
                )
            )
        ) to Credits(
            id = 1,
            cast = listOf(
                Cast(
                    id = 1,
                    name = "Marlon Brando",
                    popularity = 70.664,
                    character = "Don Vito Corleone",
                    path = "/fuTEPMsBtV1zE98ujPONbKiYDc2.jpg"
                )
            ),
            crew = listOf(
                Crew(
                    id = 1776,
                    name = "Francis Ford Coppola",
                    popularity = 12.772,
                    job = "Writing",
                    department = "Screenplay",
                    path = "/mGKkVp3l9cipPt10AqoQnwaPrfI.jpg"
                )
            )
        )

    )
}

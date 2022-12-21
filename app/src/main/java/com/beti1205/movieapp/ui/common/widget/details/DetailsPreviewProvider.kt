package com.beti1205.movieapp.ui.common.widget.details

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.common.Genre
import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetails

class DetailsPreviewProvider : PreviewParameterProvider<MovieDetails> {
    override val values = sequenceOf(
        MovieDetails(
            id = 985939,
            title = "The Godfather II",
            overview = "In the continuing saga of the Corleone crime family, a young " +
                "Vito Corleone grows up in Sicily and in 1910s New York.",
            voteAverage = 7.4,
            posterPath = "/9Baumh5J9N1nJUYzNkm0xsgjpwY.jpg",
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
        ),
        MovieDetails(
            id = 496243,
            title = "Parasite",
            overview = "All unemployed, Ki-taek's family takes peculiar interest in the wealthy " +
                "and glamorous Parks for their livelihood until they get entangled in an" +
                " unexpected incident.",
            voteAverage = 8.5,
            posterPath = null,
            releaseDate = null,
            genres = emptyList()
        )
    )
}

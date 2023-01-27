package com.beti1205.movieapp.ui.movies.common

import androidx.paging.PagingData
import com.beti1205.movieapp.feature.movies.data.Movie

object PagingMoviePreviewDataProvider {

    val movie = Movie(
        id = 985939,
        title = "The Godfather II",
        overview = "For best friends Becky and Hunter, life is all about conquering fears and pushing limits.",
        popularity = 5456.118,
        adult = false,
        voteCount = 1300,
        voteAverage = 7.4,
        language = "en",
        posterPath = "/spCAxD99U1A6jsiePFoqdEcY0dG.jpg",
        originalTitle = "Fall",
        releaseDate = "2022-08-11"
    )
    val pagingData: PagingData<Movie> = PagingData.from(
        listOf(
            movie,
            movie.copy(
                id = 98877,
                title = "Fall"
            ),
            movie.copy(
                id = 98874,
                title = "Matrix"
            )
        )
    )
}

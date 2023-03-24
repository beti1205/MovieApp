/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui

import com.beti1205.movieapp.common.data.ApiResponse
import com.beti1205.movieapp.feature.movies.data.Movie

object MovieDataProvider {

    val movies = listOf(
        Movie(
            id = 985939,
            title = "Fall",
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
    )

    val apiResponse = ApiResponse(page = 1, totalResults = 1, totalPages = 1, movies)
}

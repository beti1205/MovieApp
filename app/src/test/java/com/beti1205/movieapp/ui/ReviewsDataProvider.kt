/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui

import com.beti1205.movieapp.feature.reviews.data.AuthorDetails
import com.beti1205.movieapp.feature.reviews.data.Review
import com.beti1205.movieapp.feature.reviews.data.ReviewsResult

object ReviewsDataProvider {
    val reviewResult = ReviewsResult(
        id = 1,
        results = listOf(
            Review(
                author = "crastana",
                content = "The best movie ever...A masterpiece by the young and talented " +
                    "Francis Ford Coppola, about a Mob family and their drama, the story telling" +
                    " is perfect, the acting good, sometimes a little over the top in the case of " +
                    "Thalia Shire (the sister of the director).The 70's were the best" +
                    " years for Hollywood.",
                id = "62d5ea2fe93e95095cbddefe",
                createdAt = "2022-07-18T23:18:07.748Z",
                updatedAt = "2022-07-26T14:21:07.910Z",
                authorDetails = AuthorDetails(avatar = null)
            )
        )
    )
}

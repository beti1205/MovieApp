package com.beti1205.movieapp.ui.common.widget.reviews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.feature.reviews.data.Review

class ReviewsPreviewProvider : PreviewParameterProvider<List<Review>> {
    override val values = sequenceOf(
        listOf(
            Review(
                author = "crastana",
                content = "The best movie ever...A masterpiece by the young and talented " +
                    "Francis Ford Coppola, about a Mob family and their drama, the story telling" +
                    " is perfect, the acting good, sometimes a little over the top in the case of " +
                    "Thalia Shire (the sister of the director).The 70's were the best" +
                    " years for Hollywood.",
                id = "62d5ea2fe93e95095cbddefe",
                createdAt = "2022-07-18T23:18:07.748Z",
                updatedAt = "2022-07-26T14:21:07.910Z"
            ),
            Review(
                author = "jkbbr549",
                content = "This is by far the greatest movie of all time!  Even " +
                    "better than the first Godfather!",
                id = "553637669251416518002602",
                createdAt = "2015-04-21T11:41:26.541Z",
                updatedAt = "2021-06-23T15:57:34.131Z"
            )
        ),
        emptyList()
    )
}

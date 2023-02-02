/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common.widget.list

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.feature.movies.data.Movie

class ListItemPreviewProvider : PreviewParameterProvider<Movie> {
    override val values = sequenceOf(
        Movie(
            id = 985939,
            title = "The Godfather II",
            overview = "In the continuing saga of the Corleone crime family, a young " +
                "Vito Corleone grows up in Sicily and in 1910s New York.",
            popularity = 63.191,
            adult = false,
            voteCount = 10364,
            voteAverage = 8.602,
            language = "en",
            posterPath = "/spCAxD99U1A6jsiePFoqdEcY0dG.jpg",
            originalTitle = "The Godfather Part II",
            releaseDate = "1974-12-20"
        ),
        Movie(
            id = 497,
            title = "The Green Mile",
            overview = "A supernatural tale set on death row in a Southern prison," +
                " where gentle giant John Coffey possesses the mysterious power to " +
                "heal people's ailments.When the cell block's head guard, Paul Edgecomb," +
                " recognizes Coffey's miraculous gift, he tries desperately to help stave off " +
                "the condemned man's execution.",
            popularity = 95.84,
            adult = false,
            voteCount = 14744,
            voteAverage = 8.509,
            language = "en",
            posterPath = null,
            originalTitle = "The Green Mile",
            releaseDate = null
        )
    )
}

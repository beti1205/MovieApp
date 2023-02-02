/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account.widget.preview

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails
import com.beti1205.movieapp.feature.accountdetails.data.Avatar
import com.beti1205.movieapp.feature.accountdetails.data.Tmdb
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.feature.tvseries.data.TVSeries

class AccountScreenPreviewProvider : PreviewParameterProvider<AccountScreenData> {
    override val values = sequenceOf(
        AccountScreenData(
            isLoggedIn = true,
            hasError = false,
            denied = false,
            account = AccountDetails(
                id = 1,
                name = "Beata",
                username = "beti1205",
                avatar = Avatar(tmdb = Tmdb(avatarPath = null))
            ),
            movies = listOf(
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
            ),
            tvSeries = listOf(
                TVSeries(
                    popularity = 632.02,
                    id = 80752,
                    overview = "A virus has decimated humankind. Those who survived emerged blind",
                    name = "See",
                    firstAirDate = "2019-11-01",
                    originalName = "See",
                    voteAverage = 8.3,
                    posterPath = "/lKDIhc9FQibDiBQ57n3ELfZCyZg.jpg"
                )
            )
        ),
        AccountScreenData(
            isLoggedIn = false,
            hasError = false,
            denied = true,
            account = null,
            movies = emptyList(),
            tvSeries = emptyList()
        )
    )
}

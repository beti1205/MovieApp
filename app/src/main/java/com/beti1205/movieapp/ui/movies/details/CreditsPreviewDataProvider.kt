package com.beti1205.movieapp.ui.movies.details

import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.feature.fetchcredits.data.Crew

object CreditsPreviewDataProvider {
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
    val cast = listOf(
        Cast(
            id = 1,
            name = "Grace Caroline Currey",
            popularity = 8.9,
            character = "Becky",
            path = "/6chZcnjWEiFfpmB6D5BR9YUeIs9.jpg"
        )
    )
}

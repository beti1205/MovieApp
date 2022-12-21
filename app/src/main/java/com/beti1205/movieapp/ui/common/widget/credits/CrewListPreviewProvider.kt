package com.beti1205.movieapp.ui.common.widget.credits

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.feature.fetchcredits.data.Crew

class CrewListPreviewProvider : PreviewParameterProvider<List<Crew>> {
    override val values = sequenceOf(
        listOf(
            Crew(
                id = 90812,
                name = "Scott Mann",
                popularity = 7.356,
                job = "Director",
                department = "Directing",
                path = "/8WygpUzfdfztZQqxGE5zn3rCedJ.jpg"
            ),
            Crew(
                id = 83,
                name = "Gray Frederickson",
                popularity = 1.4,
                job = "Associate Producer",
                department = "Production",
                path = null
            )
        )
    )
}

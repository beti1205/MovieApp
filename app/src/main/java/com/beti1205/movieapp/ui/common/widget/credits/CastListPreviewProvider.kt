package com.beti1205.movieapp.ui.common.widget.credits

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.feature.credits.data.Cast

class CastListPreviewProvider : PreviewParameterProvider<List<Cast>> {
    override val values = sequenceOf(
        listOf(
            Cast(
                id = 1,
                name = "Grace Caroline Currey",
                popularity = 8.9,
                character = "Becky",
                path = "/6chZcnjWEiFfpmB6D5BR9YUeIs9.jpg"
            ),
            Cast(
                id = 3,
                name = "Joe Lo Grippo",
                popularity = 10.0,
                character = "Sonny's Bodyguard(uncredited)",
                path = null
            )
        )
    )
}

package com.beti1205.movieapp.ui.tvseries.details.widget.episodes

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.feature.fetchtvepisodes.data.Episode

class EpisodeItemPreviewProvider : PreviewParameterProvider<Episode> {
    override val values = sequenceOf(
        Episode(
            id = 1019694,
            name = "Mijo",
            overview = "As his troubles escalate to a boiling point, Jimmy finds himself in dire straits.",
            posterPath = "/8XFhyx4xFCY2nZPThgOUF42G4fI.jpg",
            episodeAirDate = "2015-02-09",
            episodeNumber = 1
        ),
        Episode(
            id = 62085,
            name = "Pilot",
            overview = "When an unassuming high school chemistry teacher discovers he has " +
                "a rare form of lung cancer, he decides to team up with a former student" +
                " and create a top of the line crystal meth in a used RV, to provide for" +
                " his family once he is gone.",
            posterPath = "/ydlY3iPfeOAvu8gVqrxPoMvzNCn.jpg",
            episodeAirDate = "2008-01-20",
            episodeNumber = 1
        )
    )
}

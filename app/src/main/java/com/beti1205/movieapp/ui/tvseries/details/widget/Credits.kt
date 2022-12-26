package com.beti1205.movieapp.ui.tvseries.details.widget

import androidx.compose.runtime.Composable
import com.beti1205.movieapp.feature.fetchcredits.data.Credits
import com.beti1205.movieapp.ui.common.widget.credits.SectionCast
import com.beti1205.movieapp.ui.common.widget.credits.SectionCrew

@Composable
fun Credits(
    credits: Credits,
    onPersonClicked: (Int) -> Unit
) {
    val castNotEmpty = credits.cast.isNotEmpty()
    val crewNotEmpty = credits.crew.isNotEmpty()

    if (castNotEmpty) {
        SectionCast(cast = credits.cast, onPersonClicked = onPersonClicked)
    }
    if (crewNotEmpty) {
        SectionCrew(crew = credits.crew, onPersonClicked = onPersonClicked)
    }
}

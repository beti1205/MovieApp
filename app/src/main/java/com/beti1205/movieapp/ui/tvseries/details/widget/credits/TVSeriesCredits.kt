/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.details.widget.credits

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.beti1205.movieapp.feature.credits.data.Credits
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.common.widget.credits.SectionCast
import com.beti1205.movieapp.ui.common.widget.credits.SectionCrew

@Composable
fun TVSeriesCredits(
    credits: Credits,
    onPersonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    val castNotEmpty = credits.cast.isNotEmpty()
    val crewNotEmpty = credits.crew.isNotEmpty()

    Column(modifier = modifier) {
        if (castNotEmpty) {
            SectionCast(cast = credits.cast, onPersonClicked = onPersonClicked)
            StandardDivider()
        }
        if (crewNotEmpty) {
            SectionCrew(crew = credits.crew, onPersonClicked = onPersonClicked)
            StandardDivider()
        }
    }
}

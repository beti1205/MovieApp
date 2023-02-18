/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.tvseries.common.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.ExperimentalMaterialApi
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.tvseries.data.TVSeries
import com.beti1205.movieapp.ui.common.widget.list.ListItem
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@OptIn(ExperimentalMaterialApi::class)
@Composable
fun TVSeriesItem(
    tvSeries: TVSeries,
    onTVSeriesClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        onClick = { onTVSeriesClicked(tvSeries.id) },
        shape = RoundedCornerShape(16.dp),
        contentColor = MaterialTheme.colors.onPrimary,
        modifier = modifier.padding(8.dp),
        elevation = 16.dp
    ) {
        ListItem(tvSeries.posterPath, tvSeries.name, tvSeries.votes)
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun TVSeriesItemPreview(
    @PreviewParameter(TVSeriesItemScreenPreviewProvider::class)
    tvSeries: TVSeries
) {
    MovieAppTheme {
        Surface {
            TVSeriesItem(
                tvSeries = tvSeries,
                onTVSeriesClicked = {}
            )
        }
    }
}

class TVSeriesItemScreenPreviewProvider : PreviewParameterProvider<TVSeries> {
    override val values = sequenceOf(
        TVSeries(
            popularity = 632.02,
            id = 80752,
            overview = "A virus has decimated humankind. Those who survived emerged blind",
            name = "See",
            firstAirDate = "2019-11-01",
            originalName = "See",
            voteAverage = 8.3,
            posterPath = "/lKDIhc9FQibDiBQ57n3ELfZCyZg.jpg"
        ),
        TVSeries(
            popularity = 424.992,
            id = 1396,
            overview = "Edward and Alphonse Elric's reckless disregard for alchemy's " +
                "fun­damental laws ripped half of Ed's limbs from his body and left " +
                "Al's soul clinging to a cold suit of armor. To restore what I  was " +
                "lost, the brothers scour a war-torn land for the Philosopher's" +
                " Sto­ne, a fabled relic which grants the ability to perform alchemy " +
                "in impossible ways.",
            name = "Fullmetal Alchemist: Brotherhood",
            firstAirDate = null,
            originalName = "Fullmetal Alchemist: Brotherhood",
            voteAverage = 8.9,
            posterPath = null
        )
    )
}

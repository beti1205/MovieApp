package com.beti1205.movieapp.ui.persondetails.widget

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.movies.details.widget.EmptyStateMessage
import com.beti1205.movieapp.ui.persondetails.PersonDetailsPreviewDataProvider
import com.beti1205.movieapp.ui.persondetails.Section
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun PersonDetails(
    details: PersonDetails?,
    hasCreditsError: Boolean,
    sections: List<Section>,
    onExpandedChanged: (Section, Boolean) -> Unit,
    onMovieClicked: (Int) -> Unit,
    onTVSeriesClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    LazyColumn(modifier = modifier) {
        item {
            Person(details)
            StandardDivider()
        }

        when {
            hasCreditsError -> item {
                EmptyStateMessage()
            }
            else -> sections.forEach { section ->
                section(
                    section = section,
                    onExpandedChanged = { expanded ->
                        onExpandedChanged(
                            section,
                            expanded
                        )
                    },
                    onMovieClicked = onMovieClicked,
                    onTVSeriesClicked = onTVSeriesClicked
                )
            }
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PersonDetailsPreview() {
    MovieAppTheme {
        Surface {
            PersonDetails(
                details = PersonDetailsPreviewDataProvider.personDetails,
                sections = PersonDetailsPreviewDataProvider.sectionsList,
                hasCreditsError = false,
                onExpandedChanged = { _, _ -> },
                onMovieClicked = {},
                onTVSeriesClicked = {}
            )
        }
    }
}

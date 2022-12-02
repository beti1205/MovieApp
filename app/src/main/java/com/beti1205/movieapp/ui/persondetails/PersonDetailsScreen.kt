package com.beti1205.movieapp.ui.persondetails

import android.content.res.Configuration
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.persondetails.widget.Person
import com.beti1205.movieapp.ui.persondetails.widget.section
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun PersonDetailsScreen(
    viewModel: PersonDetailsViewModel,
    onMovieClicked: (Int) -> Unit
) {
    val personDetails by viewModel.personDetails.collectAsState()
    val sections by viewModel.sections.collectAsState()

    PersonDetailsScreen(
        details = personDetails,
        sections = sections,
        onExpandedChanged = viewModel::onSectionExpandedChanged,
        onMovieClicked = onMovieClicked
    )
}

@Composable
fun PersonDetailsScreen(
    details: PersonDetails?,
    onExpandedChanged: (Section, Boolean) -> Unit,
    sections: List<Section>,
    onMovieClicked: (Int) -> Unit
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            LazyColumn {
                item {
                    Person(details)
                    Spacer(modifier = Modifier.height(8.dp))
                    StandardDivider()
                }

                sections.forEach { section ->
                    section(
                        section = section,
                        onExpandedChanged = { expanded ->
                            onExpandedChanged(
                                section,
                                expanded
                            )
                        },
                        onMovieClicked = onMovieClicked
                    )
                }
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
fun PersonDetailsScreenPreview() {
    MovieAppTheme {
        Surface {
            PersonDetailsScreen(
                details = PersonDetailsPreviewDataProvider.personDetails,
                sections = PersonDetailsPreviewDataProvider.sectionsList,
                onExpandedChanged = { _, _ -> },
                onMovieClicked = {}
            )
        }
    }
}

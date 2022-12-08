package com.beti1205.movieapp.ui.persondetails

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.ui.common.widget.Loader
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
    val isLoading by viewModel.isLoading.collectAsState()

    PersonDetailsScreen(
        details = personDetails,
        sections = sections,
        isLoading = isLoading,
        onExpandedChanged = viewModel::onSectionExpandedChanged,
        onMovieClicked = onMovieClicked
    )
}

@Composable
fun PersonDetailsScreen(
    details: PersonDetails?,
    sections: List<Section>,
    isLoading: Boolean,
    onExpandedChanged: (Section, Boolean) -> Unit,
    onMovieClicked: (Int) -> Unit
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            when {
                isLoading -> Loader()
                else -> LazyColumn {
                    item {
                        Person(details)
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
                isLoading = false,
                onExpandedChanged = { _, _ -> },
                onMovieClicked = {}
            )
        }
    }
}

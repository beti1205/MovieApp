package com.beti1205.movieapp.ui.persondetails

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.ui.common.widget.Loader
import com.beti1205.movieapp.ui.movies.details.widget.EmptyStateMessage
import com.beti1205.movieapp.ui.persondetails.widget.PersonDetails
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun PersonDetailsScreen(
    viewModel: PersonDetailsViewModel,
    onMovieClicked: (Int) -> Unit
) {
    val personDetails by viewModel.personDetails.collectAsState()
    val sections by viewModel.sections.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val hasError by viewModel.hasError.collectAsState()
    val hasCreditsError by viewModel.hasCreditsError.collectAsState()

    PersonDetailsScreen(
        details = personDetails,
        sections = sections,
        isLoading = isLoading,
        hasError = hasError,
        hasCreditsError = hasCreditsError,
        onExpandedChanged = viewModel::onSectionExpandedChanged,
        onMovieClicked = onMovieClicked
    )
}

@Composable
fun PersonDetailsScreen(
    details: PersonDetails?,
    sections: List<Section>,
    isLoading: Boolean,
    hasError: Boolean,
    hasCreditsError: Boolean,
    onExpandedChanged: (Section, Boolean) -> Unit,
    onMovieClicked: (Int) -> Unit
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            when {
                isLoading -> Loader()
                hasError -> EmptyStateMessage()
                else -> PersonDetails(
                    details = details,
                    hasCreditsError = hasCreditsError,
                    sections = sections,
                    onExpandedChanged = onExpandedChanged,
                    onMovieClicked = onMovieClicked
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
fun PersonDetailsScreenPreview() {
    MovieAppTheme {
        Surface {
            PersonDetailsScreen(
                details = PersonDetailsPreviewDataProvider.personDetails,
                sections = PersonDetailsPreviewDataProvider.sectionsList,
                isLoading = false,
                hasError = false,
                hasCreditsError = false,
                onExpandedChanged = { _, _ -> },
                onMovieClicked = {}
            )
        }
    }
}

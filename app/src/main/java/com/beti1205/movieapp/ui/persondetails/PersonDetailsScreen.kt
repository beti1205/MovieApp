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
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCast
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCrew
import com.beti1205.movieapp.ui.common.widget.Loader
import com.beti1205.movieapp.ui.movies.details.widget.EmptyStateMessage
import com.beti1205.movieapp.ui.persondetails.widget.PersonDetails
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun PersonDetailsScreen(
    viewModel: PersonDetailsViewModel,
    onMovieClicked: (Int) -> Unit,
    onTVSeriesClicked: (Int) -> Unit
) {
    val personDetails by viewModel.personDetails.collectAsState()
    val movieCastSection by viewModel.movieCastSection.collectAsState()
    val tvCastSection by viewModel.tvCastSection.collectAsState()
    val movieCrewSection by viewModel.movieCrewSection.collectAsState()
    val tvCrewSection by viewModel.tvCrewSection.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()
    val hasError by viewModel.hasError.collectAsState()
    val hasCreditsError by viewModel.hasCreditsError.collectAsState()

    PersonDetailsScreen(
        details = personDetails,
        movieCastSection = movieCastSection,
        tvCastSection = tvCastSection,
        movieCrewSection = movieCrewSection,
        tvCrewSection = tvCrewSection,
        isLoading = isLoading,
        hasError = hasError,
        hasCreditsError = hasCreditsError,
        onExpandedChanged = viewModel::onSectionExpandedChanged,
        onMovieClicked = onMovieClicked,
        onTVSeriesClicked = onTVSeriesClicked
    )
}

@Composable
fun PersonDetailsScreen(
    details: PersonDetails?,
    movieCastSection: Section<PersonMovieCast>,
    tvCastSection: Section<PersonTVSeriesCast>,
    movieCrewSection: Section<PersonMovieCrew>,
    tvCrewSection: Section<PersonTVSeriesCrew>,
    isLoading: Boolean,
    hasError: Boolean,
    hasCreditsError: Boolean,
    onExpandedChanged: (SectionType, Boolean) -> Unit,
    onMovieClicked: (Int) -> Unit,
    onTVSeriesClicked: (Int) -> Unit
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            when {
                isLoading -> Loader()
                hasError -> EmptyStateMessage()
                else -> PersonDetails(
                    details = details,
                    hasCreditsError = hasCreditsError,
                    movieCastSection = movieCastSection,
                    tvCastSection = tvCastSection,
                    movieCrewSection = movieCrewSection,
                    tvCrewSection = tvCrewSection,
                    onExpandedChanged = onExpandedChanged,
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
fun PersonDetailsScreenPreview() {
    MovieAppTheme {
        Surface {
            PersonDetailsScreen(
                details = PersonDetailsPreviewDataProvider.personDetails,
                isLoading = false,
                hasError = false,
                hasCreditsError = false,
                onExpandedChanged = { _, _ -> },
                onMovieClicked = {},
                onTVSeriesClicked = {},
                movieCastSection = PersonDetailsPreviewDataProvider.sectionMovieCast,
                tvCastSection = PersonDetailsPreviewDataProvider.sectionTVSeriesCast,
                movieCrewSection = PersonDetailsPreviewDataProvider.sectionMovieCrew,
                tvCrewSection = PersonDetailsPreviewDataProvider.sectionTVCrew
            )
        }
    }
}

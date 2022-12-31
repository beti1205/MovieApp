package com.beti1205.movieapp.ui.persondetails

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCast
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCrew
import com.beti1205.movieapp.ui.common.widget.EmptyStateMessage
import com.beti1205.movieapp.ui.common.widget.Loader
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
fun PersonDetailsScreenPreview(
    @PreviewParameter(PersonDetailsScreenPreviewProvider::class)
    personDetailsScreenData: PersonDetailsScreenData
) {
    MovieAppTheme {
        Surface {
            PersonDetailsScreen(
                details = personDetailsScreenData.personDetails,
                isLoading = personDetailsScreenData.isLoading,
                hasError = personDetailsScreenData.hasError,
                hasCreditsError = personDetailsScreenData.hasCreditsError,
                onExpandedChanged = { _, _ -> },
                onMovieClicked = {},
                onTVSeriesClicked = {},
                movieCastSection = personDetailsScreenData.movieCastSection,
                tvCastSection = personDetailsScreenData.tvCastSection,
                movieCrewSection = personDetailsScreenData.movieCrewSection,
                tvCrewSection = personDetailsScreenData.tvCrewSection
            )
        }
    }
}

data class PersonDetailsScreenData(
    val personDetails: PersonDetails?,
    val isLoading: Boolean,
    val hasError: Boolean,
    val hasCreditsError: Boolean,
    val movieCastSection: Section<PersonMovieCast>,
    val tvCastSection: Section<PersonTVSeriesCast>,
    val movieCrewSection: Section<PersonMovieCrew>,
    val tvCrewSection: Section<PersonTVSeriesCrew>
)

class PersonDetailsScreenPreviewProvider : PreviewParameterProvider<PersonDetailsScreenData> {
    override val values = sequenceOf(
        PersonDetailsScreenData(
            PersonDetails(
                id = 31,
                birthday = "1956-07-09",
                deathday = null,
                name = "Tom Hanks",
                biography = "Thomas Jeffrey Hanks (born July 9, 1956) ...",
                popularity = 69.464,
                birthPlace = "Concord, California, USA",
                personPoster = null
            ),
            isLoading = false,
            hasError = false,
            hasCreditsError = false,
            Section(
                items = listOf(SectionDataProvider.personMovieCast),
                expanded = false,
                expandable = false
            ),
            Section(
                items = listOf(SectionDataProvider.personTVCast),
                expanded = false,
                expandable = false
            ),
            Section(
                items = listOf(SectionDataProvider.personMovieCrew),
                expanded = false,
                expandable = false
            ),
            Section(
                items = listOf(SectionDataProvider.personTVCrew),
                expanded = false,
                expandable = false
            )

        ),
        PersonDetailsScreenData(
            personDetails = null,
            isLoading = false,
            hasError = true,
            hasCreditsError = false,
            movieCastSection = Section(
                items = emptyList(),
                expanded = false,
                expandable = false
            ),
            tvCastSection = Section(
                items = emptyList(),
                expanded = false,
                expandable = false
            ),
            movieCrewSection = Section(
                items = emptyList(),
                expanded = false,
                expandable = false
            ),
            tvCrewSection = Section(
                items = emptyList(),
                expanded = false,
                expandable = false
            )

        )
    )
}

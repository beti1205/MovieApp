package com.beti1205.movieapp.ui.persondetails.widget

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCast
import com.beti1205.movieapp.feature.fetchpersontvseriescredits.data.PersonTVSeriesCrew
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.movies.details.widget.EmptyStateMessage
import com.beti1205.movieapp.ui.persondetails.PersonDetailsPreviewDataProvider
import com.beti1205.movieapp.ui.persondetails.Section
import com.beti1205.movieapp.ui.persondetails.SectionType
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun PersonDetails(
    details: PersonDetails?,
    hasCreditsError: Boolean,
    movieCastSection: Section<PersonMovieCast>,
    tvCastSection: Section<PersonTVSeriesCast>,
    movieCrewSection: Section<PersonMovieCrew>,
    tvCrewSection: Section<PersonTVSeriesCrew>,
    onExpandedChanged: (SectionType, Boolean) -> Unit,
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
            else -> {
                if (movieCastSection.isNotEmpty() || tvCastSection.isNotEmpty()) {
                    item {
                        SectionHeader(text = stringResource(id = R.string.section_header_movie_cast))
                    }
                }

                movieCastSection(movieCastSection, onMovieClicked, onExpandedChanged)
                tvCastSection(tvCastSection, onTVSeriesClicked, onExpandedChanged)

                if (movieCastSection.isNotEmpty() || tvCastSection.isNotEmpty()) {
                    item {
                        StandardDivider()
                    }
                }

                if (movieCrewSection.isNotEmpty() || tvCrewSection.isNotEmpty()) {
                    item {
                        SectionHeader(text = stringResource(id = R.string.section_header_movie_crew))
                    }
                }

                movieCrewSection(movieCrewSection, onMovieClicked, onExpandedChanged)
                tvCrewSection(tvCrewSection, onTVSeriesClicked, onExpandedChanged)

                if (movieCrewSection.isNotEmpty() || tvCrewSection.isNotEmpty()) {
                    item {
                        StandardDivider()
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
fun PersonDetailsPreview() {
    MovieAppTheme {
        Surface {
            PersonDetails(
                details = PersonDetailsPreviewDataProvider.personDetails,
                hasCreditsError = false,
                movieCastSection = PersonDetailsPreviewDataProvider.sectionMovieCast,
                tvCastSection = PersonDetailsPreviewDataProvider.sectionTVSeriesCast,
                movieCrewSection = PersonDetailsPreviewDataProvider.sectionMovieCrew,
                tvCrewSection = PersonDetailsPreviewDataProvider.sectionTVCrew,
                onExpandedChanged = { _, _ -> },
                onMovieClicked = {},
                onTVSeriesClicked = {}
            )
        }
    }
}

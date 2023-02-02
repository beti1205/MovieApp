/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.persondetails.widget

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.persondetails.data.PersonDetails
import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCast
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCrew
import com.beti1205.movieapp.ui.common.widget.Error
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.persondetails.Section
import com.beti1205.movieapp.ui.persondetails.SectionDataProvider
import com.beti1205.movieapp.ui.persondetails.SectionType
import com.beti1205.movieapp.ui.persondetails.widget.person.Person
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
                Error()
            }
            else -> {
                val castSectionsNotEmpty = movieCastSection.isNotEmpty() ||
                    tvCastSection.isNotEmpty()
                val crewSectionsNotEmpty = movieCrewSection.isNotEmpty() ||
                    tvCrewSection.isNotEmpty()

                if (castSectionsNotEmpty) {
                    item {
                        SectionHeader(text = stringResource(id = R.string.section_header_movie_cast))
                    }
                }

                movieCastSection(movieCastSection, onMovieClicked, onExpandedChanged)
                tvCastSection(tvCastSection, onTVSeriesClicked, onExpandedChanged)

                if (castSectionsNotEmpty && crewSectionsNotEmpty) {
                    item {
                        StandardDivider()
                    }
                }

                if (crewSectionsNotEmpty) {
                    item {
                        SectionHeader(text = stringResource(id = R.string.section_header_movie_crew))
                    }
                }

                movieCrewSection(movieCrewSection, onMovieClicked, onExpandedChanged)
                tvCrewSection(tvCrewSection, onTVSeriesClicked, onExpandedChanged)
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
fun PersonDetailsPreview(
    @PreviewParameter(PersonDetailsPreviewProvider::class) personDetailsData: PersonDetailsData
) {
    MovieAppTheme {
        Surface {
            PersonDetails(
                details = personDetailsData.personDetails,
                hasCreditsError = personDetailsData.hasCreditsError,
                movieCastSection = personDetailsData.movieCastSection,
                tvCastSection = personDetailsData.tvCastSection,
                movieCrewSection = personDetailsData.movieCrewSection,
                tvCrewSection = personDetailsData.tvCrewSection,
                onExpandedChanged = { _, _ -> },
                onMovieClicked = {},
                onTVSeriesClicked = {}
            )
        }
    }
}

data class PersonDetailsData(
    val personDetails: PersonDetails,
    val hasCreditsError: Boolean,
    val movieCastSection: Section<PersonMovieCast>,
    val tvCastSection: Section<PersonTVSeriesCast>,
    val movieCrewSection: Section<PersonMovieCrew>,
    val tvCrewSection: Section<PersonTVSeriesCrew>
)

class PersonDetailsPreviewProvider : PreviewParameterProvider<PersonDetailsData> {
    override val values = sequenceOf(
        PersonDetailsData(
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
        PersonDetailsData(
            personDetails = PersonDetails(
                id = 31,
                birthday = "1956-07-09",
                deathday = null,
                name = "Tom Hanks",
                biography = "Thomas Jeffrey Hanks (born July 9, 1956) ...",
                popularity = 69.464,
                birthPlace = "Concord, California, USA",
                personPoster = null
            ),
            hasCreditsError = true,
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

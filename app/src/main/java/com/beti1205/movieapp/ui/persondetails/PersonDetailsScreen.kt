package com.beti1205.movieapp.ui.persondetails

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.ui.common.widget.DetailsTitle
import com.beti1205.movieapp.ui.persondetails.widget.ExpandableOverview
import com.beti1205.movieapp.ui.persondetails.widget.PersonBirthPlace
import com.beti1205.movieapp.ui.persondetails.widget.PersonBirthday
import com.beti1205.movieapp.ui.persondetails.widget.PersonDeathDay
import com.beti1205.movieapp.ui.persondetails.widget.PersonDetailsPoster
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun PersonDetailsScreen(viewModel: PersonDetailsViewModel) {
    val personDetails by viewModel.personDetails.collectAsState()

    PersonDetailsScreen(
        personDetails = personDetails
    )
}

@Composable
fun PersonDetailsScreen(
    personDetails: PersonDetails?
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Column(
                modifier = Modifier
                    .padding(horizontal = 16.dp)
                    .verticalScroll(rememberScrollState())
            ) {
                if (personDetails != null) {
                    Row {
                        PersonDetailsPoster(posterPath = personDetails.personPoster)
                        Column(
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .align(Alignment.CenterVertically)
                        ) {
                            DetailsTitle(title = personDetails.name)
                            PersonBirthday(personDetails.birthday)
                            PersonBirthPlace(personDetails.birthPlace)
                            PersonDeathDay(personDetails.deathday)
                        }
                    }
                    ExpandableOverview(personDetails.biography)
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
                personDetails = PersonDetailsPreviewDataProvider.personDetails
            )
        }
    }
}

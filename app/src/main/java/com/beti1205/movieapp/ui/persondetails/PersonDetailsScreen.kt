package com.beti1205.movieapp.ui.persondetails

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.feature.fetchpersonmoviecredits.data.PersonMovieCrew
import com.beti1205.movieapp.ui.common.widget.DetailsTitle
import com.beti1205.movieapp.ui.common.widget.StandardDivider
import com.beti1205.movieapp.ui.persondetails.widget.ExpandableOverview
import com.beti1205.movieapp.ui.persondetails.widget.PersonBirthPlace
import com.beti1205.movieapp.ui.persondetails.widget.PersonBirthday
import com.beti1205.movieapp.ui.persondetails.widget.PersonDeathDay
import com.beti1205.movieapp.ui.persondetails.widget.PersonDetailsPoster
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun PersonDetailsScreen(viewModel: PersonDetailsViewModel) {
    val personDetails by viewModel.personDetails.collectAsState()
    val personMovieCast by viewModel.personMovieCast.collectAsState()
    val personMovieCrew by viewModel.personMovieCrew.collectAsState()

    PersonDetailsScreen(
        details = personDetails,
        cast = personMovieCast,
        crew = personMovieCrew
    )
}

@Composable
fun PersonDetailsScreen(
    details: PersonDetails?,
    cast: List<PersonMovieCast>,
    crew: List<PersonMovieCrew>
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            var expanded by remember {
                mutableStateOf(false)
            }

            val castItems by remember(expanded, cast) {
                derivedStateOf { if (expanded) cast else cast.take(5) }
            }

            LazyColumn {
                item {
                    Person(details)
                    Spacer(modifier = Modifier.height(8.dp))
                    StandardDivider()
                }
                item {
                    SectionHeader(text = "Acting")
                }
                items(castItems) { item ->
                    FilmographyItem(item)
                }
                item {
                    Button(onClick = { expanded = !expanded }) {
                        Text(text = "Show more")
                    }
                }
            }
        }
    }
}

@Composable
private fun SectionHeader(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6,
        modifier = modifier.padding(vertical = 8.dp)
    )
}

@Composable
private fun Person(personDetails: PersonDetails?) {
    if (personDetails != null) {
        Column(modifier = Modifier.padding(horizontal = 16.dp)) {
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

@Composable
private fun FilmographyItem(item: PersonMovieCast) {
    Row(
        modifier = Modifier
            .padding(bottom = 8.dp)
    ) {
        Text(
            text = item.releaseDate,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            fontStyle = FontStyle.Italic
        )
        Text(
            text = item.title,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(1F),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = item.character,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 8.dp)
                .weight(1F)
        )
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
                cast = emptyList(),
                crew = emptyList()
            )
        }
    }
}

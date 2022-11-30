package com.beti1205.movieapp.ui.persondetails.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchpersondetails.data.PersonDetails
import com.beti1205.movieapp.ui.common.widget.DetailsTitle

@Composable
fun Person(personDetails: PersonDetails?) {
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

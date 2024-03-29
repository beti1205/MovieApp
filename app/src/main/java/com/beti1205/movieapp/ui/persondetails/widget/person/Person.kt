/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.persondetails.widget.person

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.persondetails.data.PersonDetails
import com.beti1205.movieapp.ui.common.widget.details.DetailsTitle
import com.beti1205.movieapp.ui.persondetails.widget.ExpandableOverview
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun Person(
    personDetails: PersonDetails?,
    modifier: Modifier = Modifier
) {
    if (personDetails != null) {
        Column(modifier = modifier.padding(horizontal = 16.dp)) {
            Row {
                PersonPoster(posterPath = personDetails.personPoster)
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
            if (personDetails.biography.isNotEmpty()) {
                ExpandableOverview(personDetails.biography)
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
fun PersonPreview(@PreviewParameter(PersonPreviewProvider::class) personDetails: PersonDetails) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Person(
                personDetails = personDetails
            )
        }
    }
}

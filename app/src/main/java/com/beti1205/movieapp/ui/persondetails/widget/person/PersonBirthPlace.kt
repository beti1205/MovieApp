/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.persondetails.widget.person

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.beti1205.movieapp.feature.persondetails.data.PersonDetails
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun PersonBirthPlace(
    place: String?,
    modifier: Modifier = Modifier
) {
    Text(
        text = place ?: "",
        style = MaterialTheme.typography.subtitle2,
        modifier = modifier
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PersonBirthPlacePreview(
    @PreviewParameter(PersonPreviewProvider::class)
    personDetails: PersonDetails
) {
    MovieAppTheme {
        Surface {
            PersonBirthPlace(
                place = personDetails.birthPlace
            )
        }
    }
}

package com.beti1205.movieapp.ui.persondetails.widget

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.ui.persondetails.PersonDetailsPreviewDataProvider
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
fun PersonBirthPlacePreview() {
    MovieAppTheme {
        Surface {
            PersonBirthPlace(
                place = PersonDetailsPreviewDataProvider.personDetails.birthPlace
            )
        }
    }
}

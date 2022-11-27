package com.beti1205.movieapp.ui.persondetails.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.persondetails.PersonDetailsPreviewDataProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun PersonDeathDay(
    deathDay: String?,
    modifier: Modifier = Modifier
) {
    Text(
        text = stringResource(
            id = R.string.person_details_deathday_label,
            deathDay ?: "-"
        ),
        modifier = modifier.padding(top = 8.dp),
        style = MaterialTheme.typography.subtitle2
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PersonDeathDayPreview() {
    MovieAppTheme {
        Surface {
            PersonDeathDay(
                deathDay = PersonDetailsPreviewDataProvider.personDetails.deathday
            )
        }
    }
}

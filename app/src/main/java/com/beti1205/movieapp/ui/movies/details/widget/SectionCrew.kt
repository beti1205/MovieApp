package com.beti1205.movieapp.ui.movies.details.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.fetchcredits.data.Crew
import com.beti1205.movieapp.ui.movies.details.MovieDetailsPreviewDataProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun SectionCrew(
    crew: List<Crew>?,
    onPersonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        SectionTitle(text = stringResource(id = R.string.crew))
        CrewList(
            crew = crew,
            onPersonClicked = onPersonClicked
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SectionCrewPreview() {
    MovieAppTheme {
        Surface {
            SectionCrew(
                crew = MovieDetailsPreviewDataProvider.credits.crew,
                onPersonClicked = {}
            )
        }
    }
}

package com.beti1205.movieapp.ui.persondetails.widget

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.persondetails.PersonDetailsPreviewDataProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun FilmographyItem(
    date: String,
    name: String,
    description: String,
    movieId: Int,
    onMovieClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onMovieClicked(movieId) }
            .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
    ) {
        Text(
            text = date.ifEmpty { stringResource(R.string.filmography_date_unknown) },
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            fontStyle = FontStyle.Italic,
            modifier = Modifier
                .weight(1F)
        )
        Text(
            text = name,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(2F),
            fontWeight = FontWeight.Bold
        )
        Text(
            text = description,
            style = MaterialTheme.typography.body2,
            textAlign = TextAlign.Start,
            modifier = Modifier
                .padding(start = 16.dp)
                .weight(2F)
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun FilmographyItemPreview() {
    MovieAppTheme {
        Surface {
            FilmographyItem(
                date = PersonDetailsPreviewDataProvider.personMovieCast.releaseDate,
                name = PersonDetailsPreviewDataProvider.personMovieCast.title,
                description = PersonDetailsPreviewDataProvider.personMovieCast.character,
                movieId = 1,
                onMovieClicked = {}
            )
        }
    }
}

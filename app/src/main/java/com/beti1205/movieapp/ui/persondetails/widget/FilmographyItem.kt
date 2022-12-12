package com.beti1205.movieapp.ui.persondetails.widget

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
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
    rating: String,
    modifier: Modifier = Modifier,
    onMovieClicked: (Int) -> Unit = {}
) {
    Row(
        modifier = modifier
            .clickable { onMovieClicked(movieId) }
            .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
    ) {
        Date(date = date)
        Title(name = name, modifier = Modifier.weight(1F))
        Description(description = description, modifier = Modifier.weight(1F))
        Rating(rating = rating)
    }
}

@Composable
private fun Description(
    description: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = description,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Start,
        modifier = modifier
            .padding(start = 16.dp)
    )
}

@Composable
private fun Title(
    name: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = name,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Start,
        modifier = modifier
            .padding(start = 16.dp),
        fontWeight = FontWeight.Bold
    )
}

@Composable
private fun Date(date: String) {
    Text(
        text = date.ifEmpty { stringResource(R.string.filmography_unknown) },
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Start,
        fontStyle = FontStyle.Italic
    )
}

@Composable
private fun Rating(
    rating: String,
    modifier: Modifier = Modifier
) {
    Row(verticalAlignment = Alignment.CenterVertically, modifier = modifier) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_star_24),
            contentDescription = null,
            tint = MaterialTheme.colors.secondary
        )

        Text(
            text = rating,
            style = MaterialTheme.typography.body2
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
                description = PersonDetailsPreviewDataProvider.personMovieCast.character
                    ?: "unknown",
                movieId = 1,
                rating = "9.0",
                onMovieClicked = {}
            )
        }
    }
}

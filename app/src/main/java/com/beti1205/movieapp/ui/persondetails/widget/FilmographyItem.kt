/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.persondetails.widget

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Star
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCast
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun FilmographyItem(
    date: String,
    name: String,
    description: String,
    id: Int,
    rating: String,
    onItemRowClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .clickable { onItemRowClicked(id) }
            .padding(bottom = 8.dp, start = 16.dp, end = 16.dp)
            .fillMaxWidth()
    ) {
        Date(date = date, modifier = Modifier.weight(1F))
        Title(name = name, modifier = Modifier.weight(4F))
        Description(description = description, modifier = Modifier.weight(4F))
        Rating(rating = rating, modifier = Modifier.weight(2F))
    }
}

@Composable
private fun Description(
    description: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = description.ifEmpty { stringResource(R.string.filmography_unknown) },
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
private fun Date(date: String, modifier: Modifier = Modifier) {
    Text(
        text = date.ifEmpty { "-" },
        style = MaterialTheme.typography.body2,
        textAlign = if (date.isNotEmpty()) TextAlign.Start else TextAlign.Center,
        fontStyle = FontStyle.Italic,
        modifier = modifier
    )
}

@Composable
private fun Rating(
    rating: String,
    modifier: Modifier = Modifier
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier.padding(start = 12.dp)
    ) {
        Icon(
            imageVector = Icons.Outlined.Star,
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
fun FilmographyItemPreview(
    @PreviewParameter(FilmographyItemPreviewProvider::class)
    personMovieCast: PersonMovieCast
) {
    MovieAppTheme {
        Surface {
            FilmographyItem(
                date = personMovieCast.releaseDate,
                name = personMovieCast.title,
                description = personMovieCast.character
                    ?: "unknown",
                id = personMovieCast.id,
                rating = personMovieCast.votes,
                onItemRowClicked = {}
            )
        }
    }
}

class FilmographyItemPreviewProvider : PreviewParameterProvider<PersonMovieCast> {
    override val values = sequenceOf(
        PersonMovieCast(
            character = "Himself",
            title = "Murch: Walter Murch on Editing",
            popularity = 1.0,
            id = 1,
            overview = "",
            voteCount = 100,
            voteAverage = 1.0,
            creditId = "1",
            releaseDate = "2007-01-26",
            genreIds = listOf(1),
            originalLanguage = "",
            originalTitle = "",
            posterPath = null
        ),
        PersonMovieCast(
            character = "Jake Sully",
            title = "Avatar: The Way of Water",
            popularity = 4334.092,
            id = 76600,
            overview = "Set more than a decade after the events of the first film, learn the" +
                " story of the Sully family (Jake, Neytiri, and their kids), the trouble " +
                "that follows them, the lengths they go to keep each other safe, the battles " +
                "they fight to stay alive, and the tragedies they endure.",
            voteCount = 954,
            voteAverage = 8.07,
            creditId = "52fe4943c3a368484e122b49",
            releaseDate = "2022-12-14",
            genreIds = listOf(
                878,
                28,
                12
            ),
            originalLanguage = "en",
            originalTitle = "Avatar: The Way of Water",
            posterPath = "/94xxm5701CzOdJdUEdIuwqZaowx.jpg"
        )
    )
}

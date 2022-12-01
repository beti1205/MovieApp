package com.beti1205.movieapp.ui.persondetails.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R

@Composable
fun FilmographyItem(
    date: String,
    name: String,
    description: String
) {
    Row(
        modifier = Modifier
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

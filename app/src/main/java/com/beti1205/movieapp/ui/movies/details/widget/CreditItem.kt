package com.beti1205.movieapp.ui.movies.details.widget

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.ui.movies.details.MovieDetailsPreviewDataProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun CreditItem(
    path: String?,
    name: String,
    id: Int,
    description: String,
    onPersonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier.clickable { onPersonClicked(id) }) {
        CreditsPoster(posterPath = path)
        Text(
            text = name,
            modifier = Modifier.padding(top = 8.dp),
            style = MaterialTheme.typography.body1
        )
        Text(
            text = description,
            modifier = Modifier.padding(top = 8.dp),
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
fun CreditItemPreview() {
    MovieAppTheme {
        Surface {
            CreditItem(
                path = MovieDetailsPreviewDataProvider.cast.first().path,
                name = MovieDetailsPreviewDataProvider.cast.first().name,
                id = MovieDetailsPreviewDataProvider.cast.first().id,
                description = MovieDetailsPreviewDataProvider.cast.first().character,
                onPersonClicked = {}
            )
        }
    }
}

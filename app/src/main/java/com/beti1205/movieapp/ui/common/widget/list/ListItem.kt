package com.beti1205.movieapp.ui.common.widget.list

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.ui.common.widget.Rating
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun ListItem(
    posterPath: String?,
    title: String,
    votes: String,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        ItemPoster(posterPath = posterPath)
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            ItemTitle(
                title = title,
                modifier = Modifier.weight(1f)
            )
            Rating(votes = votes, modifier = Modifier.padding(top = 8.dp, end = 8.dp))
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun ListItemPreview() {
    MovieAppTheme {
        Surface {
            ListItem(
                posterPath = null,
                title = "Better Call Saul",
                votes = "1.0"
            )
        }
    }
}

package com.beti1205.movieapp.ui.common.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.fetchmovies.data.Movie
import com.beti1205.movieapp.ui.common.widget.list.ListItemPreviewProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun Rating(
    votes: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_stars_24),
            contentDescription = null,
            tint = MaterialTheme.colors.secondary
        )
        Text(
            text = votes,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun RatingPreview(@PreviewParameter(ListItemPreviewProvider::class) movie: Movie) {
    MovieAppTheme {
        Surface {
            Rating(votes = movie.votes)
        }
    }
}

package com.beti1205.movieapp.ui.common.widget.details

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun ReleaseDate(
    releaseDate: String?,
    modifier: Modifier = Modifier
) {
    Text(
        text = releaseDate ?: "",
        style = MaterialTheme.typography.caption,
        color = Color.Gray,
        modifier = modifier
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun ReleaseDatePreview() {
    MovieAppTheme {
        Surface {
            ReleaseDate(releaseDate = "12.05.1995")
        }
    }
}

package com.beti1205.movieapp.ui.common.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun Overview(
    overview: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = overview,
        style = MaterialTheme.typography.body2,
        modifier = modifier.padding(top = 8.dp),
        textAlign = TextAlign.Justify
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun OverviewPreview() {
    MovieAppTheme() {
        Surface {
            Overview(overview = "For best friends Becky and Hunter...")
        }
    }
}

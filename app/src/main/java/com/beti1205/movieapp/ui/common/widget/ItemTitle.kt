package com.beti1205.movieapp.ui.common.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
fun ItemTitle(title: String) {
    Text(
        text = title,
        style = MaterialTheme.typography.subtitle2,
        modifier = Modifier
            .width(200.dp)
            .padding(8.dp),
        textAlign = TextAlign.Center
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun ItemTitlePreview() {
    MovieAppTheme {
        Surface {
            ItemTitle(title = "Better Call Saul")
        }
    }
}

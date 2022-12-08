package com.beti1205.movieapp.ui.persondetails.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun SectionButton(
    expanded: Boolean,
    onExpandedChanged: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    ShowMoreButton(
        expanded = expanded,
        onClick = { onExpandedChanged(!expanded) },
        modifier = modifier.padding(start = 16.dp)
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun SectionButtonPreview() {
    MovieAppTheme {
        Surface {
            SectionButton(
                expanded = false,
                onExpandedChanged = {}
            )
        }
    }
}

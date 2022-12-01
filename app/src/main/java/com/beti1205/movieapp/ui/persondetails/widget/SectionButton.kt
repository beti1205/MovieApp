package com.beti1205.movieapp.ui.persondetails.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun SectionButton(
    onExpandedChanged: (Boolean) -> Unit,
    expanded: Boolean
) {
    TextButton(
        onClick = { onExpandedChanged(!expanded) },
        modifier = Modifier.padding(start = 16.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.onPrimary
        )
    ) {
        if (!expanded) {
            Text(text = stringResource(R.string.section_button_show_more))
        } else {
            Text(text = stringResource(R.string.section_button_show_less))
        }
    }
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
                onExpandedChanged = {},
                expanded = false
            )
        }
    }
}

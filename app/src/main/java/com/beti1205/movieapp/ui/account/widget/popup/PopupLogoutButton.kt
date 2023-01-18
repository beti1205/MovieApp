package com.beti1205.movieapp.ui.account.widget.popup

import android.content.res.Configuration
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.theme.SonicSilver

@Composable
fun PopupLogoutButton(onDeleteSession: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onDeleteSession,
        modifier = modifier.padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = SonicSilver
        )
    ) {
        Text(
            text = stringResource(R.string.account_log_out_button),
            style = MaterialTheme.typography.button
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun ShowMoreButtonPreview() {
    MovieAppTheme {
        Surface {
            PopupLogoutButton(
                onDeleteSession = {}
            )
        }
    }
}

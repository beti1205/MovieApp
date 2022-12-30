package com.beti1205.movieapp.ui.common.widget.details

import android.content.res.Configuration
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun ReviewsButton(
    id: Int,
    onButtonClicked: (Int) -> Unit,
    modifier: Modifier = Modifier
) {
    TextButton(
        onClick = { onButtonClicked(id) },
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.primaryVariant,
            contentColor = MaterialTheme.colors.secondary
        ),
        modifier = modifier
    ) {
        Text(text = stringResource(R.string.reviews_button_label))
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun ReviewsButtonPreview() {
    MovieAppTheme {
        Surface {
            ReviewsButton(id = 1, onButtonClicked = {})
        }
    }
}

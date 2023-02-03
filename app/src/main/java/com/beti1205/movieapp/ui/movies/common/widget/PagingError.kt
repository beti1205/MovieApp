/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.common.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun PagingError(
    onRetryClick: () -> Unit,
    modifier: Modifier = Modifier,
    textColor: Color = MaterialTheme.colors.onPrimary
) {
    Column(
        modifier = modifier
            .padding(16.dp)
    ) {
        Text(
            text = stringResource(id = R.string.connection_error_message),
            modifier = Modifier.padding(8.dp),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.subtitle2,
            color = textColor
        )
        Button(
            onClick = onRetryClick,
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.CenterHorizontally),
            colors = ButtonDefaults.buttonColors(backgroundColor = Color.White)
        ) {
            Text(
                text = stringResource(id = R.string.retry_button_label),
                color = Color.Black
            )
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun PagingErrorPreview() {
    MovieAppTheme {
        Surface {
            PagingError(onRetryClick = { })
        }
    }
}

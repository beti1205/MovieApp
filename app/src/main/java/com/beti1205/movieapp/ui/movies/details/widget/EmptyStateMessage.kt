package com.beti1205.movieapp.ui.movies.details.widget

import android.content.res.Configuration
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.theme.SonicSilver

@Composable
fun EmptyStateMessage(
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_broken_image_24),
            contentDescription = null,
            modifier = Modifier
                .height(140.dp)
                .width(140.dp),
            tint = SonicSilver
        )
        Text(
            text = stringResource(id = R.string.connection_error_message),
            color = SonicSilver,
            style = MaterialTheme.typography.subtitle2,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun EmptyStateMessagePreview() {
    EmptyStateMessage()
}

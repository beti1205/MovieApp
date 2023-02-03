/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common.widget.details

import android.content.res.Configuration
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun DetailsTitle(
    title: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = title,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.secondary,
        modifier = modifier
    )
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun TitlePreview() {
    MovieAppTheme {
        Surface {
            DetailsTitle(title = "Better Call Saul")
        }
    }
}

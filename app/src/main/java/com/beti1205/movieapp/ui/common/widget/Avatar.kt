/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common.widget

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun Avatar(
    text: String?,
    modifier: Modifier = Modifier,
    color: Color = MaterialTheme.colors.secondary,
    size: Dp = 32.dp,
    onClick: () -> Unit = {}
) {
    Box(
        modifier = modifier
            .size(size)
            .clip(CircleShape)
            .background(color = color)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text?.first()?.toString() ?: "", color = MaterialTheme.colors.onSecondary)
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun AvatarPreview() {
    MovieAppTheme {
        Surface {
            Avatar(
                text = "B",
                onClick = {}
            )
        }
    }
}

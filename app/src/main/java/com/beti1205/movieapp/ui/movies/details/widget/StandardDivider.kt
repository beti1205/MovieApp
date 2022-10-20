package com.beti1205.movieapp.ui.movies.details.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun StandardDivider(modifier: Modifier = Modifier) {
    Divider(
        modifier = modifier.padding(horizontal = 16.dp, vertical = 8.dp)
    )
}

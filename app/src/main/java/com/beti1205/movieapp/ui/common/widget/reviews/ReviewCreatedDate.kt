package com.beti1205.movieapp.ui.common.widget.reviews

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun ReviewCreatedDate(createdDate: String, modifier: Modifier = Modifier) {
    Text(
        text = createdDate,
        fontWeight = FontWeight.Bold,
        modifier = modifier,
        style = MaterialTheme.typography.body2
    )
}

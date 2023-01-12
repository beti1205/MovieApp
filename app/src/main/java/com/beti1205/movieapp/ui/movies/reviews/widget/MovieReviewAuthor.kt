package com.beti1205.movieapp.ui.movies.reviews.widget

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight

@Composable
fun MovieReviewAuthor(author: String, modifier: Modifier = Modifier) {
    Text(
        text = author,
        color = MaterialTheme.colors.secondary,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.body2,
        modifier = modifier
    )
}

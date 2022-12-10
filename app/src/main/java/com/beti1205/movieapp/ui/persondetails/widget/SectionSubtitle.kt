package com.beti1205.movieapp.ui.persondetails.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp

@Composable
fun SectionSubtitle(
    text: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = text,
        style = MaterialTheme.typography.subtitle1,
        color = MaterialTheme.colors.secondary,
        fontWeight = FontWeight.Bold,
        modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)
    )
}

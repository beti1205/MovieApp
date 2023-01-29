package com.beti1205.movieapp.ui.account.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AccountSectionHeader(text: String, modifier: Modifier = Modifier) {
    Text(
        text = text,
        style = MaterialTheme.typography.h6,
        color = MaterialTheme.colors.secondary,
        modifier = modifier.padding(horizontal = 16.dp)
    )
}

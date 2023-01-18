package com.beti1205.movieapp.ui.account.widget.popup

import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun AccountUsername(username: String, modifier: Modifier = Modifier) {
    Text(
        text = username,
        style = MaterialTheme.typography.body1,
        modifier = modifier.padding(top = 8.dp)
    )
}

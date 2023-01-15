package com.beti1205.movieapp.ui.common.widget.list

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.ui.theme.SonicSilver

@Composable
fun ListTopAppBar(title: String, onSearchClicked: () -> Unit) {
    TopAppBar(
        title = { Text(title) },
        actions = {
            IconButton(onClick = { onSearchClicked() }) {
                Icon(
                    imageVector = Icons.Outlined.Search,
                    contentDescription = null,
                    tint = SonicSilver
                )
            }
        },
        elevation = 0.dp
    )
}

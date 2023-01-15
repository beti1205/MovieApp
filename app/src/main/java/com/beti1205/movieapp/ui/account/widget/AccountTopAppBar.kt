package com.beti1205.movieapp.ui.account.widget

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.ui.theme.SonicSilver

@Composable
fun AccountTopAppBar(isLoggedIn: Boolean, onDeleteSession: () -> Unit) {
    TopAppBar(
        title = { Text("Account") },
        actions = {
            if (isLoggedIn) {
                IconButton(onClick = { onDeleteSession() }) {
                    Icon(
                        imageVector = Icons.Outlined.ExitToApp,
                        contentDescription = null,
                        tint = SonicSilver
                    )
                }
            }
        },
        elevation = 0.dp
    )
}

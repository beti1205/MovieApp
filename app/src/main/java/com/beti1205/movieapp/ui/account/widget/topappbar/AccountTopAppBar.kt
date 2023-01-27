package com.beti1205.movieapp.ui.account.widget.topappbar

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails

@Composable
fun AccountTopAppBar(
    account: AccountDetails?,
    isLoggedIn: Boolean,
    onAvatarClicked: () -> Unit
) {
    TopAppBar(
        title = { Text("Account") },
        actions = {
            if (isLoggedIn) {
                Avatar(text = account?.name, onClick = onAvatarClicked)
            }
        },
        elevation = 0.dp
    )
}

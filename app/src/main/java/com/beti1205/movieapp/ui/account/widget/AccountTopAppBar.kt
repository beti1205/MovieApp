package com.beti1205.movieapp.ui.account.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchaccountdetails.data.AccountDetails

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

@Composable
fun Avatar(
    text: String?,
    onClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(color = MaterialTheme.colors.secondary)
            .clickable { onClick() },
        contentAlignment = Alignment.Center
    ) {
        Text(text = text?.first()?.toString() ?: "")
    }
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account.widget.topappbar

import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails
import com.beti1205.movieapp.ui.common.widget.Avatar

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

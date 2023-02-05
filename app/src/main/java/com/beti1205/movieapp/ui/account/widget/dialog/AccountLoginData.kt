/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account.widget.dialog

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails

@Composable
fun AccountLoginData(
    account: AccountDetails,
    onDeleteSession: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(all = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column {
            if (!account.name.isNullOrEmpty()) {
                AccountName(name = account.name)
            }
            AccountUsername(username = account.username)
        }
        DialogLogoutButton(onDeleteSession = onDeleteSession)
    }
}

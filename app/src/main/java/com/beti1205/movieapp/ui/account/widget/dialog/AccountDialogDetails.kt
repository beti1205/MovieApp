/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account.widget.dialog

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails
import com.beti1205.movieapp.ui.common.widget.AccountAvatar

@Composable
fun AccountDialogDetails(
    account: AccountDetails,
    onDeleteSession: () -> Unit
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.padding(horizontal = 8.dp)
    ) {
        val avatarPath = account.avatar.tmdb.avatarPath

        if (avatarPath != null) {
            AccountAvatar(
                avatar = avatarPath,
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            )
        }
        AccountLoginData(
            account = account,
            onDeleteSession = onDeleteSession,
            modifier = Modifier.weight(1f)
        )
    }
}

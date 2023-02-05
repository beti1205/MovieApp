/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.account.widget.dialog

import android.content.res.Configuration
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails
import com.beti1205.movieapp.feature.accountdetails.data.Avatar
import com.beti1205.movieapp.feature.accountdetails.data.Tmdb
import com.beti1205.movieapp.ui.theme.MovieAppTheme

private val appBarHeight: Dp = 56.dp

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun AccountDialog(
    account: AccountDetails,
    onDeleteSession: () -> Unit,
    onDismissRequest: () -> Unit
) {
    Dialog(
        onDismissRequest = onDismissRequest,
        properties = DialogProperties(usePlatformDefaultWidth = false)
    ) {
        MovieAppTheme {
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .clickable(
                        interactionSource = remember { MutableInteractionSource() },
                        indication = null
                    ) {
                        onDismissRequest()
                    },
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                AccountDialogContent(
                    account = account,
                    onDeleteSession = onDeleteSession,
                    onDismissRequest = onDismissRequest
                )
            }
        }
    }
}

@Composable
private fun AccountDialogContent(
    account: AccountDetails,
    onDeleteSession: () -> Unit,
    onDismissRequest: () -> Unit,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .padding(top = appBarHeight)
            .fillMaxWidth(0.95f)
            .clip(MaterialTheme.shapes.medium),
        color = MaterialTheme.colors.primaryVariant
    ) {
        Column(modifier = Modifier.padding(vertical = 8.dp)) {
            AccountDialogTitle(onDismissRequest = onDismissRequest)
            AccountDialogDetails(
                account = account,
                onDeleteSession = onDeleteSession
            )
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun AccountDialogContentPreview(
    @PreviewParameter(AccountDetailsPreviewProvider::class) accountDetails: AccountDetails
) {
    MovieAppTheme {
        Surface {
            AccountDialogContent(
                account = accountDetails,
                onDeleteSession = {},
                onDismissRequest = {}
            )
        }
    }
}

class AccountDetailsPreviewProvider : PreviewParameterProvider<AccountDetails> {
    override val values = sequenceOf(
        AccountDetails(
            id = 1,
            name = null,
            username = "beti1205",
            avatar = Avatar(tmdb = Tmdb(avatarPath = null))
        ),
        AccountDetails(
            id = 2,
            name = "Beata",
            username = "beti1205",
            avatar = Avatar(tmdb = Tmdb(avatarPath = "/rAH4L3kWjwxoClGw5eFZXoCC0tg.jpg"))
        )
    )
}

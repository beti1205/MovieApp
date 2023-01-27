package com.beti1205.movieapp.ui.account.widget.popup

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails
import com.beti1205.movieapp.feature.accountdetails.data.Avatar
import com.beti1205.movieapp.feature.accountdetails.data.Tmdb
import com.beti1205.movieapp.ui.theme.MovieAppTheme

private val appBarHeight: Dp = 56.dp

@Composable
fun AccountPopup(
    account: AccountDetails?,
    onDeleteSession: () -> Unit,
    onDismissRequest: () -> Unit
) {
    val y = with(LocalDensity.current) { appBarHeight.toPx().toInt() }
    Popup(
        alignment = Alignment.TopCenter,
        offset = IntOffset(0, y),
        properties = PopupProperties(focusable = true),
        onDismissRequest = onDismissRequest
    ) {
        MovieAppTheme {
            Surface(
                elevation = 8.dp
            ) {
                AccountPopupContent(account, onDeleteSession)
            }
        }
    }
}

@Composable
private fun AccountPopupContent(
    account: AccountDetails?,
    onDeleteSession: () -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxWidth(0.95f)
            .clip(MaterialTheme.shapes.medium)
    ) {
        if (account != null) {
            val avatarPath = account.avatar.tmdb.avatarPath
            Row(verticalAlignment = Alignment.CenterVertically) {
                if (avatarPath != null) {
                    AccountAvatar(avatar = avatarPath, modifier = Modifier.weight(1f).padding(8.dp))
                }
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    AccountUsername(account.username)
                    if (account.name?.isNotEmpty() == true) {
                        AccountName(account.name)
                    }
                    PopupLogoutButton(onDeleteSession)
                }
            }
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true
)
@Composable
fun AccountPopupContentPreview(
    @PreviewParameter(AccountDetailsPreviewProvider::class) accountDetails: AccountDetails
) {
    MovieAppTheme {
        Surface {
            AccountPopupContent(
                account = accountDetails,
                onDeleteSession = {}
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

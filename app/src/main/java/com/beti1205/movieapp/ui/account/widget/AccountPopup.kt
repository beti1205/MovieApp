package com.beti1205.movieapp.ui.account.widget

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import androidx.compose.ui.window.PopupProperties
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.fetchaccountdetails.data.AccountDetails
import com.beti1205.movieapp.ui.theme.MovieAppTheme
import com.beti1205.movieapp.ui.theme.SonicSilver

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
            Surface {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.95f)
                        .clip(MaterialTheme.shapes.medium)
                        .background(MaterialTheme.colors.primaryVariant)
                ) {
                    if (account != null) {
                        val avatarPath = account.avatar.tmdb.avatarPath
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            if (avatarPath != null) {
                                AccountAvatar(avatar = avatarPath, modifier = Modifier.weight(1f))
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
        }
    }
}

@Composable
private fun AccountName(name: String, modifier: Modifier = Modifier) {
    Text(
        text = name,
        style = MaterialTheme.typography.body1,
        modifier = modifier
    )
}

@Composable
private fun AccountUsername(username: String, modifier: Modifier = Modifier) {
    Text(
        text = username,
        style = MaterialTheme.typography.body1,
        modifier = modifier.padding(top = 8.dp)
    )
}

@Composable
private fun PopupLogoutButton(onDeleteSession: () -> Unit, modifier: Modifier = Modifier) {
    Button(
        onClick = onDeleteSession,
        modifier = modifier.padding(vertical = 8.dp),
        colors = ButtonDefaults.buttonColors(
            backgroundColor = SonicSilver
        )
    ) {
        Text(
            text = stringResource(R.string.account_log_out_button),
            style = MaterialTheme.typography.button
        )
    }
}

@Composable
private fun AccountAvatar(avatar: String, modifier: Modifier = Modifier) {
    val baseUrl = "https://image.tmdb.org/t/p/w500"
    val imageUri = "$baseUrl$avatar"
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(imageUri)
            .crossfade(true)
            .build(),
        contentDescription = null,
        modifier = modifier
    )
}

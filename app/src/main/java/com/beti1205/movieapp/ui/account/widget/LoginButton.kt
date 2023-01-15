package com.beti1205.movieapp.ui.account.widget

import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import com.beti1205.movieapp.R

@Composable
fun LoginButton(onLoginClicked: () -> Unit) {
    TextButton(
        onClick = onLoginClicked,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = MaterialTheme.colors.secondary,
            contentColor = MaterialTheme.colors.onSecondary
        )
    ) {
        Text(text = stringResource(R.string.account_log_in_button))
    }
}

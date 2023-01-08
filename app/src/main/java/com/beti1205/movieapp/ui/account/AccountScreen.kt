package com.beti1205.movieapp.ui.account

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun AccountScreen(viewModel: AccountViewModel) {
    val token by viewModel.requestToken.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val hasError by viewModel.hasError.collectAsState()
    val context = LocalContext.current

    AccountScreen(
        isLoggedIn = isLoggedIn,
        hasError = hasError,
        onLoginClicked = viewModel::getRequestToken,
        onErrorHandled = viewModel::onErrorHandled
    )

    LaunchedEffect(token) {
        if (token == null) {
            return@LaunchedEffect
        }

        val deepLinkUri = Uri.Builder().apply {
            scheme("movieapp")
            authority("app")
            appendPath("authenticate")
            appendQueryParameter("authenticationSuccess", "true")
        }.build()

        val uri = Uri.Builder().apply {
            scheme("https")
            authority("www.themoviedb.org")
            appendPath("authenticate")
            appendPath(token)
            appendQueryParameter("redirect_to", deepLinkUri.toString())
        }.build()

        val intent = Intent(Intent.ACTION_VIEW, uri)
        context.startActivity(intent)
    }
}

@Composable
fun AccountScreen(
    isLoggedIn: Boolean,
    hasError: Boolean,
    onLoginClicked: () -> Unit,
    onErrorHandled: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()

    if (hasError) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = "Login failed. Please try again!"
            )

            onErrorHandled()
        }
    }

    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Scaffold(scaffoldState = scaffoldState) { paddingValues ->
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                        .padding(16.dp),
                    contentAlignment = Alignment.Center
                ) {
                    if (isLoggedIn) {
                        Text(text = "Logged in")
                    } else {
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
                }
            }
        }
    }
}
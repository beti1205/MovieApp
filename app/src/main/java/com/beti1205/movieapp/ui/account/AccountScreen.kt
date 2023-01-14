package com.beti1205.movieapp.ui.account

import android.content.Intent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ExitToApp
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
import com.beti1205.movieapp.ui.theme.SonicSilver

@Composable
fun AccountScreen(viewModel: AccountViewModel) {
    val authUri by viewModel.authUri.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val hasError by viewModel.hasError.collectAsState()
    val denied by viewModel.denied.collectAsState()
    val context = LocalContext.current

    AccountScreen(
        isLoggedIn = isLoggedIn,
        hasError = hasError,
        denied = denied,
        onLoginClicked = viewModel::getRequestToken,
        onErrorHandled = viewModel::onErrorHandled,
        onDeniedHandled = viewModel::onDeniedHandled,
        onDeleteSession = viewModel::deleteSession
    )

    LaunchedEffect(authUri) {
        if (authUri == null) {
            return@LaunchedEffect
        }

        val intent = Intent(Intent.ACTION_VIEW, authUri)
        context.startActivity(intent)
    }
}

@Composable
fun AccountScreen(
    isLoggedIn: Boolean,
    hasError: Boolean,
    denied: Boolean,
    onLoginClicked: () -> Unit,
    onErrorHandled: () -> Unit,
    onDeniedHandled: () -> Unit,
    onDeleteSession: () -> Unit
) {
    val scaffoldState = rememberScaffoldState()
    val errorMessage = stringResource(id = R.string.generic_error_message)
    val deniedMessage = stringResource(id = R.string.denied_login_message)

    if (denied) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = deniedMessage
            )

            onDeniedHandled()
        }
    }

    if (hasError) {
        LaunchedEffect(scaffoldState.snackbarHostState) {
            scaffoldState.snackbarHostState.showSnackbar(
                message = errorMessage
            )

            onErrorHandled()
        }
    }

    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
            Scaffold(
                scaffoldState = scaffoldState,
                topBar = {
                    TopAppBar(
                        title = { Text("Account") },
                        actions = {
                            if (isLoggedIn) {
                                IconButton(onClick = { onDeleteSession() }) {
                                    Icon(
                                        imageVector = Icons.Outlined.ExitToApp,
                                        contentDescription = null,
                                        tint = SonicSilver
                                    )
                                }
                            }
                        },
                        elevation = 0.dp
                    )
                }
            ) { paddingValues ->
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

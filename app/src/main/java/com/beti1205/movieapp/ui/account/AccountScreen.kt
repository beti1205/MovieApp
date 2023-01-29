package com.beti1205.movieapp.ui.account

import android.content.Intent
import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.material.ScaffoldState
import androidx.compose.material.rememberScaffoldState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails
import com.beti1205.movieapp.feature.movies.data.Movie
import com.beti1205.movieapp.ui.account.widget.LoginButton
import com.beti1205.movieapp.ui.account.widget.favoritemovies.AccountSectionHeader
import com.beti1205.movieapp.ui.account.widget.favoritemovies.DefaultCard
import com.beti1205.movieapp.ui.account.widget.favoritemovies.FavoriteMoviesSection
import com.beti1205.movieapp.ui.account.widget.popup.AccountPopup
import com.beti1205.movieapp.ui.account.widget.preview.AccountScreenData
import com.beti1205.movieapp.ui.account.widget.preview.AccountScreenPreviewProvider
import com.beti1205.movieapp.ui.account.widget.topappbar.AccountTopAppBar
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun AccountScreen(viewModel: AccountViewModel, onMovieClicked: (Int) -> Unit) {
    val authUri by viewModel.authUri.collectAsState()
    val isLoggedIn by viewModel.isLoggedIn.collectAsState()
    val hasError by viewModel.hasError.collectAsState()
    val denied by viewModel.denied.collectAsState()
    val account by viewModel.account.collectAsState()
    val movies by viewModel.movies.collectAsState()
    val context = LocalContext.current

    AccountScreen(
        isLoggedIn = isLoggedIn,
        hasError = hasError,
        denied = denied,
        account = account,
        movies = movies,
        onLoginClicked = viewModel::fetchRequestToken,
        onErrorHandled = viewModel::onErrorHandled,
        onDeniedHandled = viewModel::onDeniedHandled,
        onDeleteSession = viewModel::deleteSession,
        onMovieClicked = onMovieClicked
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
    account: AccountDetails?,
    movies: List<Movie>,
    onLoginClicked: () -> Unit,
    onErrorHandled: () -> Unit,
    onDeniedHandled: () -> Unit,
    onDeleteSession: () -> Unit,
    onMovieClicked: (Int) -> Unit
) {
    var isPopupVisible by remember { mutableStateOf(false) }
    val scaffoldState = rememberScaffoldState()
    val errorMessage = stringResource(id = R.string.generic_error_message)
    val deniedMessage = stringResource(id = R.string.denied_login_message)

    AccountScreenContent(
        scaffoldState = scaffoldState,
        isLoggedIn = isLoggedIn,
        account = account,
        movies = movies,
        onLoginClicked = onLoginClicked,
        onMovieClicked = onMovieClicked,
        onAvatarClicked = { isPopupVisible = !isPopupVisible }
    )

    if (isLoggedIn && isPopupVisible) {
        AccountPopup(
            account = account,
            onDeleteSession = onDeleteSession,
            onDismissRequest = { isPopupVisible = false }
        )
    }

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
}

@Composable
private fun AccountScreenContent(
    scaffoldState: ScaffoldState,
    isLoggedIn: Boolean,
    account: AccountDetails?,
    movies: List<Movie>,
    onLoginClicked: () -> Unit,
    onMovieClicked: (Int) -> Unit,
    onAvatarClicked: () -> Unit
) {
    MovieAppTheme {
        Scaffold(
            scaffoldState = scaffoldState,
            topBar = {
                AccountTopAppBar(
                    account = account,
                    isLoggedIn = isLoggedIn,
                    onAvatarClicked = onAvatarClicked
                )
            }
        ) { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ) {
                if (isLoggedIn) {
                    Column(
                        modifier = Modifier
                            .padding(8.dp)
                            .fillMaxWidth()
                    ) {
                        FavoriteMoviesSection(movies = movies, onMovieClicked = onMovieClicked)

                        DefaultCard {
                            AccountSectionHeader(text = "Favorite tv series")
                        }
                    }
                } else {
                    LoginButton(
                        onLoginClicked = onLoginClicked,
                        modifier = Modifier.align(Alignment.Center)
                    )
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
fun AccountScreenPreview(
    @PreviewParameter(AccountScreenPreviewProvider::class) data: AccountScreenData
) {
    MovieAppTheme {
        AccountScreen(
            isLoggedIn = data.isLoggedIn,
            hasError = data.hasError,
            denied = data.denied,
            account = data.account,
            movies = data.movies,
            onLoginClicked = {},
            onErrorHandled = {},
            onDeniedHandled = {},
            onDeleteSession = {},
            onMovieClicked = {}
        )
    }
}

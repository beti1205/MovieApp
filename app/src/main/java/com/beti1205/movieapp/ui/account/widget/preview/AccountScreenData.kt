package com.beti1205.movieapp.ui.account.widget.preview

import com.beti1205.movieapp.feature.accountdetails.data.AccountDetails
import com.beti1205.movieapp.feature.movies.data.Movie

data class AccountScreenData(
    val isLoggedIn: Boolean,
    val hasError: Boolean,
    val denied: Boolean,
    val account: AccountDetails?,
    val movies: List<Movie>
)

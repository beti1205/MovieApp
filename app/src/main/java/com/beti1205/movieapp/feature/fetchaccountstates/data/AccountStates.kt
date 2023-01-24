package com.beti1205.movieapp.feature.fetchaccountstates.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountStates(
    val id: Int,
    val favorite: Boolean,
    val watchlist: Boolean
)

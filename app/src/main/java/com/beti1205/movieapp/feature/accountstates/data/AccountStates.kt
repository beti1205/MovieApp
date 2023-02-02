/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.accountstates.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AccountStates(
    val id: Int,
    val favorite: Boolean,
    val watchlist: Boolean
)

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.accountdetails.data

import retrofit2.http.GET
import retrofit2.http.Query

interface AccountDetailsService {
    @GET("account")
    suspend fun getAccountDetails(
        @Query("api_key") key: String,
        @Query("session_id") session: String
    ): AccountDetails
}

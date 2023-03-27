/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.accountdetails.data

import com.beti1205.movieapp.common.auth.SessionRequired
import retrofit2.http.GET

interface AccountDetailsService {
    @GET("account")
    @SessionRequired
    suspend fun getAccountDetails(): AccountDetails
}

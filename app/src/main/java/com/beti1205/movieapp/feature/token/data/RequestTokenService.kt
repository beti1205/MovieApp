/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.token.data

import retrofit2.http.GET
import retrofit2.http.Query

interface RequestTokenService {
    @GET("authentication/token/new")
    suspend fun getRequestTokenResponse(@Query("api_key") key: String): RequestTokenResponse
}

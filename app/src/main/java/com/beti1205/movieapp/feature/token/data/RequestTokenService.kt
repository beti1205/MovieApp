/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.token.data

import retrofit2.http.GET

interface RequestTokenService {
    @GET("authentication/token/new")
    suspend fun getRequestTokenResponse(): RequestTokenResponse
}

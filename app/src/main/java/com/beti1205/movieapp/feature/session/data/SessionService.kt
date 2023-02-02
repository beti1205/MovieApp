/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.session.data

import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Query

interface SessionService {
    @POST("authentication/session/new")
    suspend fun createSession(
        @Query("api_key") key: String,
        @Body body: CreateSessionBody
    ): SessionResponse
}

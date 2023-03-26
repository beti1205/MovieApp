/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.session.data

import retrofit2.http.Body
import retrofit2.http.POST

interface SessionService {
    @POST("authentication/session/new")
    suspend fun createSession(
        @Body body: CreateSessionBody
    ): SessionResponse
}

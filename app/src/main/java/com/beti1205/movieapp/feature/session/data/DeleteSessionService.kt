/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.session.data

import retrofit2.http.Body
import retrofit2.http.HTTP
import retrofit2.http.Query

interface DeleteSessionService {
    @HTTP(method = "DELETE", path = "authentication/session", hasBody = true)
    suspend fun deleteSession(
        @Query("api_key") key: String,
        @Body body: DeleteSessionBody
    ): DeleteSessionResponse
}

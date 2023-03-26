/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.session.data

import retrofit2.http.Body
import retrofit2.http.HTTP

interface DeleteSessionService {
    @HTTP(method = "DELETE", path = "authentication/session", hasBody = true)
    suspend fun deleteSession(
        @Body body: DeleteSessionBody
    ): DeleteSessionResponse
}

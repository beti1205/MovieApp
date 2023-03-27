/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.favorite.data

import com.beti1205.movieapp.common.auth.SessionRequired
import retrofit2.http.Body
import retrofit2.http.POST
import retrofit2.http.Path

interface MarkFavoriteService {
    @POST("account/{account_id}/favorite")
    @SessionRequired
    suspend fun markFavorite(
        @Path("account_id") accountId: Int,
        @Body body: MarkFavoriteBody
    )
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvepisodes.data

import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodeService {
    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun getEpisodes(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int
    ): SeasonResponse
}

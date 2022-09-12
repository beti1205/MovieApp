package com.beti1205.movieapp.feature.fetchtvepisodes.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodeService {
    @GET("tv/{tv_id}/season/{season_number}")
    suspend fun getEpisodes(
        @Path("tv_id") tvId: Int,
        @Path("season_number") seasonNumber: Int,
        @Query("api_key") key: String
    ): SeasonResponse
}

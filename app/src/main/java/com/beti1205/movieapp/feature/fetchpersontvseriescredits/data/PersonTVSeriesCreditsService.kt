package com.beti1205.movieapp.feature.fetchpersontvseriescredits.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonTVSeriesCreditsService {

    @GET("person/{person_id}/tv_credits")
    suspend fun getPersonTVSeriesCredits(
        @Path("person_id") personId: Int,
        @Query("api_key") key: String
    ): PersonTVSeriesCreditsResponse
}

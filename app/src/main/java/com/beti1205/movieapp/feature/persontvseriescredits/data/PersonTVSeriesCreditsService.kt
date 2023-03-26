/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.persontvseriescredits.data

import retrofit2.http.GET
import retrofit2.http.Path

interface PersonTVSeriesCreditsService {

    @GET("person/{person_id}/tv_credits")
    suspend fun getPersonTVSeriesCredits(
        @Path("person_id") personId: Int
    ): PersonTVSeriesCreditsResponse
}

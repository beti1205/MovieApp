/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.persondetails.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonDetailsService {
    @GET("person/{person_id}")
    suspend fun getPersonDetails(
        @Path("person_id") personId: Int,
        @Query("api_key") key: String
    ): PersonDetails
}

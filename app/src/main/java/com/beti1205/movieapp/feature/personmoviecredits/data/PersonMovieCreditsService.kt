/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.personmoviecredits.data

import retrofit2.http.GET
import retrofit2.http.Path

interface PersonMovieCreditsService {
    @GET("person/{person_id}/movie_credits")
    suspend fun getPersonMovieCredits(
        @Path("person_id") personId: Int
    ): PersonMovieCreditsResponse
}

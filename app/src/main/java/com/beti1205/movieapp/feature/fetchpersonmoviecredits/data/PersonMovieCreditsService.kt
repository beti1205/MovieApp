package com.beti1205.movieapp.feature.fetchpersonmoviecredits.data

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PersonMovieCreditsService {
    @GET("person/{person_id}/movie_credits")
    suspend fun getPersonMovieCredits(
        @Path("person_id") personId: Int,
        @Query("api_key") key: String
    ): PersonMovieCreditsResponse
}
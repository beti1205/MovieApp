package com.beti1205.movieapp.feature.fetchaccountdetails.data

import retrofit2.http.GET
import retrofit2.http.Query

interface AccountDetailsService {
    @GET("account")
    suspend fun getAccountDetails(
        @Query("api_key") key: String,
        @Query("session_id") session: String
    ): AccountDetails
}

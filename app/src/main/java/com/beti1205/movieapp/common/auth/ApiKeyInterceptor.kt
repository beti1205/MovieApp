/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.common.auth

import com.beti1205.movieapp.common.AppConfig
import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Response

class ApiKeyInterceptor @Inject constructor(private val appConfig: AppConfig) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        return chain.proceed(
            chain.request()
                .newBuilder()
                .url(
                    chain.request().url.newBuilder()
                        .addQueryParameter("api_key", appConfig.apiKey).build()
                )
                .build()
        )
    }
}

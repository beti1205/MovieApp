/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.common.auth

import javax.inject.Inject
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import retrofit2.Invocation

class SessionInterceptor @Inject constructor(
    private val authManager: AuthManager
) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val response = chain.proceed(
            if (request.getCustomAnnotation(SessionRequired::class.java) != null) {
                val url = request.url.newBuilder().addQueryParameter(
                    "session_id",
                    authManager.sessionId!!
                ).build()

                request
                    .newBuilder()
                    .url(url)
                    .build()
            } else {
                request
            }
        )

        return response
    }

    private fun <T : Annotation> Request.getCustomAnnotation(annotationClass: Class<T>): T? =
        this.tag(Invocation::class.java)?.method()?.getAnnotation(annotationClass)
}

package com.beti1205.movieapp.feature.session.data

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DeleteSessionResponse(
    val success: Boolean
)

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.reviews.data

import com.beti1205.movieapp.utils.formattedDate
import com.beti1205.movieapp.utils.toZoneDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ReviewsResult(
    val id: Int,
    val results: List<Review>
)

@Serializable
data class Review(
    val author: String,
    val content: String,
    val id: String,

    @SerialName("created_at")
    val createdAt: String,

    @SerialName("updated_at")
    val updatedAt: String?,

    @SerialName("author_details")
    val authorDetails: AuthorDetails

) {
    val createdDate: String
        get() = createdAt.toZoneDateTime().formattedDate
}

@Serializable
data class AuthorDetails(
    @SerialName("avatar_path")
    val avatar: String?
)

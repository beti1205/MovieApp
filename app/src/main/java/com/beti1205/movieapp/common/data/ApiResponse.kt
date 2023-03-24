/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.common.data

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ApiResponse<T>(
    val page: Int?,

    @SerialName("total_results")
    val totalResults: Int?,

    @SerialName("total_pages")
    val totalPages: Int?,

    @SerialName("results")
    val items: List<T>
)

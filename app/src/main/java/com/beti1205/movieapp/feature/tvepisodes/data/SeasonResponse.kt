/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.tvepisodes.data

import kotlinx.serialization.Serializable

@Serializable
data class SeasonResponse(
    val episodes: List<Episode>
)

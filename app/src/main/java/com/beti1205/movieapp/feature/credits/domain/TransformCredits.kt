/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.credits.domain

import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.feature.credits.data.Cast
import com.beti1205.movieapp.feature.credits.data.Credits
import com.beti1205.movieapp.feature.credits.data.Crew
import com.beti1205.movieapp.utils.transformImageUrl

fun Result.Success<Credits>.getTransformedCastList(imageUrl: String) = this.data.cast
    .groupBy { person -> person.id }
    .map { entry ->
        val firstRole = entry.value.first()
        Cast(
            id = entry.key,
            name = firstRole.name,
            popularity = firstRole.popularity,
            path = transformImageUrl(firstRole.path, imageUrl),
            character = entry.value.joinToString { it.character }
        )
    }

fun Result.Success<Credits>.getTransformedCrewList(imageUrl: String) = this.data.crew
    .groupBy { person -> person.id }
    .map { entry ->
        val firstRole = entry.value.first()
        Crew(
            id = entry.key,
            department = entry.value.joinToString { it.department },
            name = firstRole.name,
            popularity = firstRole.popularity,
            path = transformImageUrl(firstRole.path, imageUrl),
            job = entry.value.joinToString { it.job }
        )
    }

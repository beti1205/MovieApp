/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common.widget.list

import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.beti1205.movieapp.R

@Composable
fun ItemPoster(
    posterPath: String?,
    modifier: Modifier = Modifier
) {
    AsyncImage(
        model = ImageRequest.Builder(LocalContext.current)
            .data(posterPath)
            .crossfade(true)
            .build(),
        placeholder = painterResource(R.drawable.placeholder_image),
        error = painterResource(id = R.drawable.error_image),
        contentDescription = null,
        contentScale = ContentScale.Crop,
        modifier = modifier
            .height(300.dp)
    )
}

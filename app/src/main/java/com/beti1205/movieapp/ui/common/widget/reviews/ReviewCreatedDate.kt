/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common.widget.reviews

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun ReviewCreatedDate(createdDate: String, modifier: Modifier = Modifier) {
    Text(
        text = createdDate,
        modifier = modifier,
        style = MaterialTheme.typography.caption
    )
}

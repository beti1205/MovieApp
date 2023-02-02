/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common.widget

import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

fun <T> Modifier.listItemHorizontalPadding(
    list: List<T>,
    index: Int
) = this.padding(
    start = if (index == 0) 0.dp else 8.dp,
    end = if (index == list.size - 1) 0.dp else 8.dp
)

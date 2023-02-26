/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.common.widget.reviews

import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

@Composable
fun ReviewAuthorName(author: String, modifier: Modifier = Modifier) {
    Text(
        text = author,
        color = MaterialTheme.colors.secondary,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.body2,
        textAlign = TextAlign.Center,
        modifier = modifier
    )
}

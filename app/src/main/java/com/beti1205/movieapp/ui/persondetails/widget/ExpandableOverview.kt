package com.beti1205.movieapp.ui.persondetails.widget

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import com.beti1205.movieapp.ui.common.widget.Overview

@Composable
fun ExpandableOverview(text: String) {
    var expanded by remember {
        mutableStateOf(false)
    }

    Overview(
        overview = text,
        maxLines = if (expanded) Int.MAX_VALUE else 10,
        overflow = TextOverflow.Ellipsis,
        modifier = Modifier
            .animateContentSize()
            .clickable { expanded = !expanded }
    )
}

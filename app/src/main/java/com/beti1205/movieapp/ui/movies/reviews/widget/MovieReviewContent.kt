package com.beti1205.movieapp.ui.movies.reviews.widget

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.ui.persondetails.widget.ShowMoreButton

@Composable
fun MovieReviewContent(text: String, modifier: Modifier = Modifier) {
    var expanded by remember {
        mutableStateOf(false)
    }

    val newText by remember {
        derivedStateOf {
            when {
                expanded -> text
                else -> {
                    val strings = text.split(".").take(2).joinToString("")
                    "$strings..."
                }
            }
        }
    }

    val expandable = text.split(".").size > 2

    Column(modifier = modifier) {
        Text(
            text = if (expandable) newText else text,
            style = MaterialTheme.typography.body2,
            fontStyle = FontStyle.Italic,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.animateContentSize()
        )
        if (expandable) {
            ShowMoreButton(
                expanded = expanded,
                onClick = { expanded = !expanded },
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}
package com.beti1205.movieapp.ui.movies.details.widget

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.R

@Composable
fun Rating(
    votes: String,
    modifier: Modifier = Modifier
) {
    Row(modifier = modifier) {
        Icon(
            painter = painterResource(id = R.drawable.ic_baseline_stars_24),
            contentDescription = null,
            tint = MaterialTheme.colors.secondary
        )
        Text(
            text = votes,
            style = MaterialTheme.typography.subtitle1,
            modifier = Modifier.padding(start = 4.dp)
        )
    }
}

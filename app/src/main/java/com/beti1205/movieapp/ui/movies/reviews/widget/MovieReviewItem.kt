package com.beti1205.movieapp.ui.movies.reviews.widget

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.beti1205.movieapp.feature.fetchmoviereviews.data.MovieReview

@Composable
fun MovieReviewItem(item: MovieReview) {
    Column(modifier = Modifier.padding(horizontal = 16.dp)) {
        MovieReviewContent(item.content)
        Row(modifier = Modifier.padding(vertical = 8.dp)) {
            MovieReviewCreatedDate(item.createdDate, modifier = Modifier.weight(1f))
            MovieReviewAuthor(item.author)
        }
        Divider(
            modifier = Modifier.padding(vertical = 8.dp)
        )
    }
}

@Composable
private fun MovieReviewAuthor(author: String) {
    Text(
        text = author,
        fontWeight = FontWeight.Bold,
        style = MaterialTheme.typography.body2
    )
}

@Composable
private fun MovieReviewCreatedDate(
    createdDate: String,
    modifier: Modifier = Modifier
) {
    Text(
        text = createdDate,
        modifier = modifier,
        style = MaterialTheme.typography.body2
    )
}

@Composable
private fun MovieReviewContent(content: String) {
    Text(
        text = content,
        style = MaterialTheme.typography.body2,
        fontStyle = FontStyle.Italic
    )
}

package com.beti1205.movieapp.ui.movies.reviews.widget

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import com.beti1205.movieapp.feature.fetchmoviereviews.data.MovieReview

@Composable
fun MovieReviewList(reviews: List<MovieReview>) {
    LazyColumn {
        items(
            items = reviews,
            key = { item -> item.id }
        ) { item ->
            MovieReviewItem(item)
        }
    }
}

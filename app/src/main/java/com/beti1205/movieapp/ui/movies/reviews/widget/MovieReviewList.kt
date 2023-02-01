package com.beti1205.movieapp.ui.movies.reviews.widget

import android.content.res.Configuration
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.beti1205.movieapp.feature.reviews.data.Review
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieReviewList(reviews: List<Review>) {
    LazyColumn {
        itemsIndexed(
            items = reviews,
            key = { _, item -> item.id }
        ) { index, item ->
            MovieReviewItem(item = item, reviews = reviews, index = index)
        }
    }
}

@Preview
@Preview(
    uiMode = Configuration.UI_MODE_NIGHT_YES,
    showBackground = true,
    heightDp = 2000
)
@Composable
fun MovieReviewListPreview(
    @PreviewParameter(MovieReviewListPreviewProvider::class) reviews: List<Review>
) {
    MovieAppTheme {
        Surface {
            MovieReviewList(reviews = reviews)
        }
    }
}

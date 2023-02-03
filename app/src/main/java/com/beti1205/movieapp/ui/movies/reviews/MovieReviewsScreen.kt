/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.ui.movies.reviews

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.reviews.data.Review
import com.beti1205.movieapp.ui.common.widget.Error
import com.beti1205.movieapp.ui.common.widget.Loader
import com.beti1205.movieapp.ui.common.widget.TopAppBar
import com.beti1205.movieapp.ui.common.widget.reviews.ReviewList
import com.beti1205.movieapp.ui.common.widget.reviews.ReviewsEmptyState
import com.beti1205.movieapp.ui.common.widget.reviews.ReviewsPreviewProvider
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieReviewsScreen(
    viewModel: MovieReviewsViewModel,
    onBackPressed: () -> Unit
) {
    val reviews by viewModel.reviews.collectAsState()
    val reviewsError by viewModel.reviewsError.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    MovieReviewsScreen(
        reviews = reviews,
        reviewsError = reviewsError,
        isLoading = isLoading,
        onBackPressed = onBackPressed
    )
}

@Composable
fun MovieReviewsScreen(
    reviews: List<Review>,
    reviewsError: Boolean,
    isLoading: Boolean,
    onBackPressed: () -> Unit
) {
    MovieAppTheme {
        Scaffold(
            topBar = {
                TopAppBar(
                    title = stringResource(id = R.string.movie_review_label),
                    onBackPressed = onBackPressed
                )
            }
        ) { paddingValues ->
            Box(modifier = Modifier.fillMaxSize().padding(paddingValues))
            when {
                reviewsError -> Error()
                reviews.isNotEmpty() -> ReviewList(reviews = reviews)
                isLoading -> Loader()
                else -> ReviewsEmptyState(
                    text = stringResource(R.string.movie_reviews_empty_state)
                )
            }
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
fun MovieReviewsScreenPreview(
    @PreviewParameter(ReviewsPreviewProvider::class) reviews: List<Review>
) {
    MovieAppTheme {
        MovieReviewsScreen(
            reviews = reviews,
            reviewsError = false,
            isLoading = false,
            onBackPressed = {}
        )
    }
}

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
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.R
import com.beti1205.movieapp.feature.moviereviews.data.MovieReview
import com.beti1205.movieapp.ui.common.widget.Error
import com.beti1205.movieapp.ui.common.widget.Loader
import com.beti1205.movieapp.ui.common.widget.TopAppBar
import com.beti1205.movieapp.ui.movies.reviews.widget.MovieReviewList
import com.beti1205.movieapp.ui.movies.reviews.widget.MovieReviewsEmptyState
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
    reviews: List<MovieReview>,
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
                reviews.isNotEmpty() -> MovieReviewList(reviews = reviews)
                isLoading -> Loader()
                else -> MovieReviewsEmptyState()
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
    @PreviewParameter(MovieReviewsScreenPreviewProvider::class) reviews: List<MovieReview>
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

class MovieReviewsScreenPreviewProvider : PreviewParameterProvider<List<MovieReview>> {
    override val values = sequenceOf(
        listOf(
            MovieReview(
                author = "crastana",
                content = "The best movie ever...A masterpiece by the young and talented " +
                    "Francis Ford Coppola, about a Mob family and their drama, the story telling" +
                    " is perfect, the acting good, sometimes a little over the top in the case of " +
                    "Thalia Shire (the sister of the director).The 70's were the best" +
                    " years for Hollywood.",
                id = "62d5ea2fe93e95095cbddefe",
                createdAt = "2022-07-18T23:18:07.748Z",
                updatedAt = "2022-07-26T14:21:07.910Z"

            )
        ),
        emptyList()
    )
}

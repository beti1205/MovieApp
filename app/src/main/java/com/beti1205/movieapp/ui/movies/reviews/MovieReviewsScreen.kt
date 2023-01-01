package com.beti1205.movieapp.ui.movies.reviews

import android.content.res.Configuration
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.beti1205.movieapp.feature.fetchmoviereviews.data.MovieReview
import com.beti1205.movieapp.ui.common.widget.Error
import com.beti1205.movieapp.ui.common.widget.Loader
import com.beti1205.movieapp.ui.movies.reviews.widget.MovieReviewList
import com.beti1205.movieapp.ui.movies.reviews.widget.MovieReviewsEmptyState
import com.beti1205.movieapp.ui.theme.MovieAppTheme

@Composable
fun MovieReviewsScreen(
    viewModel: MovieReviewsViewModel
) {
    val reviews by viewModel.reviews.collectAsState()
    val reviewsError by viewModel.reviewsError.collectAsState()
    val isLoading by viewModel.isLoading.collectAsState()

    MovieReviewsScreen(
        reviews = reviews,
        reviewsError = reviewsError,
        isLoading = isLoading
    )
}

@Composable
fun MovieReviewsScreen(
    reviews: List<MovieReview>,
    reviewsError: Boolean,
    isLoading: Boolean
) {
    MovieAppTheme {
        Surface(modifier = Modifier.fillMaxSize()) {
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
        Surface {
            MovieReviewsScreen(reviews = reviews, reviewsError = false, isLoading = false)
        }
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

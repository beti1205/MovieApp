package com.beti1205.movieapp.feature.reviews.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.reviews.data.MovieReviewsService
import com.beti1205.movieapp.feature.reviews.data.ReviewsResult
import javax.inject.Inject

interface FetchMovieReviewsUseCase {

    suspend operator fun invoke(
        movieId: Int
    ): Result<ReviewsResult>
}

class FetchMovieReviewsUseCaseImpl @Inject constructor(
    private val movieReviewsService: MovieReviewsService,
    private val appConfig: AppConfig
) : FetchMovieReviewsUseCase {

    override suspend fun invoke(movieId: Int): Result<ReviewsResult> {
        return performRequest {
            movieReviewsService.getMovieReviews(
                movieId = movieId,
                key = appConfig.apiKey
            )
        }
    }
}

package com.beti1205.movieapp.feature.moviereviews.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.moviereviews.data.MovieReviewsResult
import com.beti1205.movieapp.feature.moviereviews.data.MovieReviewsService
import javax.inject.Inject

interface FetchMovieReviewsUseCase {

    suspend operator fun invoke(
        movieId: Int
    ): Result<MovieReviewsResult>
}

class FetchMovieReviewsUseCaseImpl @Inject constructor(
    private val reviewsService: MovieReviewsService,
    private val appConfig: AppConfig
) : FetchMovieReviewsUseCase {

    override suspend fun invoke(movieId: Int): Result<MovieReviewsResult> {
        return performRequest {
            reviewsService.getMovieReviews(
                movieId,
                appConfig.apiKey
            )
        }
    }
}

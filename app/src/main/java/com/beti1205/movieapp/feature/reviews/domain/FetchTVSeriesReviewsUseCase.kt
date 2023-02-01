package com.beti1205.movieapp.feature.reviews.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.reviews.data.ReviewsResult
import com.beti1205.movieapp.feature.reviews.data.TVSeriesReviewsService
import javax.inject.Inject

interface FetchTVSeriesReviewsUseCase {

    suspend operator fun invoke(
        tvId: Int
    ): Result<ReviewsResult>
}

class FetchTVSeriesReviewsUseCaseImpl @Inject constructor(
    private val tvSeriesReviewsService: TVSeriesReviewsService,
    private val appConfig: AppConfig
) : FetchTVSeriesReviewsUseCase {

    override suspend fun invoke(tvId: Int): Result<ReviewsResult> {
        return performRequest {
            tvSeriesReviewsService.getTVSeriesReviews(
                tvId = tvId,
                key = appConfig.apiKey
            )
        }
    }
}

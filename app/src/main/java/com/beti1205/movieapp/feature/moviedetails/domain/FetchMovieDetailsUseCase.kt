/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.moviedetails.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.flatMap
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.common.transformImageUrl
import com.beti1205.movieapp.feature.moviedetails.data.MovieDetails
import com.beti1205.movieapp.feature.moviedetails.data.MovieDetailsService
import javax.inject.Inject

interface FetchMovieDetailsUseCase {

    suspend operator fun invoke(
        movieId: Int
    ): Result<MovieDetails>
}

class FetchMovieDetailsUseCaseImpl @Inject constructor(
    private val movieDetailsService: MovieDetailsService,
    private val appConfig: AppConfig
) : FetchMovieDetailsUseCase {

    override suspend fun invoke(movieId: Int): Result<MovieDetails> {
        return performRequest {
            movieDetailsService.getMovieDetails(
                movieId,
                appConfig.apiKey
            )
        }.flatMap { result ->
            Result.Success(
                result.copy(
                    posterPath = transformImageUrl(result.posterPath, appConfig.imageUrl)
                )
            )
        }
    }
}

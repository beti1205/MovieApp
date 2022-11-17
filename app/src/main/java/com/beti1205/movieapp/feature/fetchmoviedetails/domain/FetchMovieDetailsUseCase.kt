package com.beti1205.movieapp.feature.fetchmoviedetails.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetails
import com.beti1205.movieapp.feature.fetchmoviedetails.data.MovieDetailsService
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
        }
    }
}

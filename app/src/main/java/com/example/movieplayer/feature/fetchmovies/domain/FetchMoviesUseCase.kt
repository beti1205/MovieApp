package com.example.movieplayer.feature.fetchmovies.domain

import com.example.movieplayer.common.ApiResponse
import com.example.movieplayer.common.AppConfig
import com.example.movieplayer.common.Result
import com.example.movieplayer.common.performRequest
import com.example.movieplayer.feature.fetchmovies.data.Movie
import com.example.movieplayer.feature.fetchmovies.data.MovieApiService
import javax.inject.Inject

interface FetchMoviesUseCase {

    suspend operator fun invoke(
        movieOrder: MovieOrder,
        page: Int
    ): Result<ApiResponse<Movie>>
}

class FetchMoviesUseCaseImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val appConfig: AppConfig
) : FetchMoviesUseCase {

    override suspend fun invoke(movieOrder: MovieOrder, page: Int): Result<ApiResponse<Movie>> {
        return performRequest {
            when (movieOrder) {
                MovieOrder.POPULAR -> movieApiService.getPopularMovies(appConfig.apiKey, page)
                MovieOrder.UPCOMING -> movieApiService.getUpcomingMovies(appConfig.apiKey, page)
                MovieOrder.TOP_RATED -> movieApiService.getTopRatedMovies(appConfig.apiKey, page)
                MovieOrder.NOW_PLAYING -> movieApiService.getNowPlayingMovies(
                    appConfig.apiKey,
                    page
                )
            }
        }
    }
}

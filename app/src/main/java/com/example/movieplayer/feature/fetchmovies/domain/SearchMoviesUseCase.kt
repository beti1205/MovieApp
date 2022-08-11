package com.example.movieplayer.feature.fetchmovies.domain

import com.example.movieplayer.common.ApiResponse
import com.example.movieplayer.common.AppConfig
import com.example.movieplayer.common.Result
import com.example.movieplayer.common.performRequest
import com.example.movieplayer.feature.fetchmovies.data.MovieApiService
import com.example.movieplayer.feature.fetchmovies.data.Movie
import javax.inject.Inject

interface SearchMoviesUseCase {

    suspend operator fun invoke(
        query: String,
        page: Int
    ): Result<ApiResponse<Movie>>
}

class SearchMoviesUseCaseImpl @Inject constructor(
    private val movieApiService: MovieApiService,
    private val appConfig: AppConfig
) : SearchMoviesUseCase {

    override suspend fun invoke(query: String, page: Int): Result<ApiResponse<Movie>> {
        return performRequest { movieApiService.getSearchedMovies(appConfig.apiKey, query, page) }
    }
}
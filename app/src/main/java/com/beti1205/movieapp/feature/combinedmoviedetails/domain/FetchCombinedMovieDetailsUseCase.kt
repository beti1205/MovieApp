/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.combinedmoviedetails.domain

import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.flatZip
import com.beti1205.movieapp.feature.accountstates.data.AccountStates
import com.beti1205.movieapp.feature.accountstates.domain.FetchMoviesAccountStatesUseCase
import com.beti1205.movieapp.feature.credits.data.Credits
import com.beti1205.movieapp.feature.credits.domain.FetchMovieCreditsUseCase
import com.beti1205.movieapp.feature.moviedetails.data.MovieDetails
import com.beti1205.movieapp.feature.moviedetails.domain.FetchMovieDetailsUseCase
import com.beti1205.movieapp.ui.movies.details.MovieDetailsScreenState
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

interface FetchCombinedMovieDetailsUseCase {

    suspend operator fun invoke(
        movieId: Int
    ): Result<MovieDetailsScreenState>
}

class FetchCombinedMovieDetailsUseCaseImpl @Inject constructor(
    private val fetchMovieDetailsUseCase: FetchMovieDetailsUseCase,
    private val fetchMovieCreditsUseCase: FetchMovieCreditsUseCase,
    private val fetchMoviesAccountStatesUseCase: FetchMoviesAccountStatesUseCase
) : FetchCombinedMovieDetailsUseCase {

    override suspend fun invoke(movieId: Int): Result<MovieDetailsScreenState> {
        return withContext(Dispatchers.IO) {
            val movieDetailsDeferredResult = async {
                fetchMovieDetailsUseCase(movieId)
            }
            val creditDeferredResult = async {
                fetchMovieCreditsUseCase(movieId)
            }
            val accountStatesDeferredResult = async {
                fetchMoviesAccountStatesUseCase(movieId)
            }

            val movieDetailsResult: Result<MovieDetails> = movieDetailsDeferredResult.await()
            val creditsResult: Result<Credits> = creditDeferredResult.await()
            val accountStatesResult: Result<AccountStates> = accountStatesDeferredResult.await()

            flatZip(
                movieDetailsResult,
                creditsResult,
                accountStatesResult
            ) { movieDetails, credits, accountStates ->
                Result.Success(
                    MovieDetailsScreenState(
                        movieDetails = movieDetails,
                        credits = credits,
                        favorite = accountStates.favorite,
                        watchlist = accountStates.watchlist
                    )
                )
            }
        }
    }
}

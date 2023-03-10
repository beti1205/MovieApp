/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.feature.personcredits.domain

import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.flatZip
import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCreditsResponse
import com.beti1205.movieapp.feature.personmoviecredits.domain.FetchPersonMovieCreditsUseCase
import com.beti1205.movieapp.feature.persontvseriescredits.data.PersonTVSeriesCreditsResponse
import com.beti1205.movieapp.feature.persontvseriescredits.domain.FetchPersonTVSeriesCreditsUseCase
import javax.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

interface FetchPersonCreditsUseCase {

    suspend operator fun invoke(
        personId: Int
    ): Result<PersonCredits>
}

class FetchPersonCreditsUseCaseImpl @Inject constructor(
    private val fetchPersonMovieCreditsUseCase: FetchPersonMovieCreditsUseCase,
    private val fetchPersonTVSeriesCreditsUseCase: FetchPersonTVSeriesCreditsUseCase
) : FetchPersonCreditsUseCase {

    override suspend fun invoke(personId: Int): Result<PersonCredits> {
        return withContext(Dispatchers.IO) {
            val movieDeferredResult = async {
                fetchPersonMovieCreditsUseCase(personId)
            }
            val tvDeferredResult = async {
                fetchPersonTVSeriesCreditsUseCase(personId)
            }

            val movieResult: Result<PersonMovieCreditsResponse> =
                movieDeferredResult.await()
            val tvResult: Result<PersonTVSeriesCreditsResponse> =
                tvDeferredResult.await()

            flatZip(
                movieResult,
                tvResult
            ) { movie, tv ->
                Result.Success(
                    PersonCredits(
                        personMovieCast = movie.cast,
                        personMovieCrew = movie.crew,
                        personTVSeriesCast = tv.cast,
                        personTVSeriesCrew = tv.crew
                    )
                )
            }
        }
    }
}

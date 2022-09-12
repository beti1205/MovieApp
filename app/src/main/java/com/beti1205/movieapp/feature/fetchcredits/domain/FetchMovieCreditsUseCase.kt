package com.beti1205.movieapp.feature.fetchcredits.domain

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.Result
import com.beti1205.movieapp.common.performRequest
import com.beti1205.movieapp.feature.fetchcredits.data.Cast
import com.beti1205.movieapp.feature.fetchcredits.data.Credits
import com.beti1205.movieapp.feature.fetchcredits.data.CreditsService
import com.beti1205.movieapp.feature.fetchcredits.data.Crew
import javax.inject.Inject

interface FetchMovieCreditsUseCase {

    suspend operator fun invoke(
        id: Int
    ): Result<Credits>
}

class FetchMovieCreditsUseCaseImpl @Inject constructor(
    private val creditsService: CreditsService,
    private val appConfig: AppConfig
) : FetchMovieCreditsUseCase {

    override suspend fun invoke(id: Int): Result<Credits> {
        val result = performRequest {
            creditsService.getMovieCredits(id, appConfig.apiKey)
        }

        return when (result) {
            is Result.Success -> Result.Success(
                result.data.copy(
                    crew = getTransformedCrewList(result),
                    cast = getTransformedCastList(result)
                )
            )
            is Result.Error -> result
        }
    }

    private fun getTransformedCastList(
        result: Result.Success<Credits>
    ) = result.data.cast
        .groupBy { person -> person.id }
        .map { entry ->
            val firstRole = entry.value.first()
            Cast(
                id = entry.key,
                name = firstRole.name,
                popularity = firstRole.popularity,
                path = firstRole.path,
                character = entry.value.joinToString { it.character }
            )
        }

    private fun getTransformedCrewList(
        result: Result.Success<Credits>
    ) = result.data.crew
        .groupBy { person -> person.id }
        .map { entry ->
            val firstRole = entry.value.first()
            Crew(
                id = entry.key,
                department = entry.value.joinToString { it.department },
                name = firstRole.name,
                popularity = firstRole.popularity,
                path = firstRole.path,
                job = entry.value.joinToString { it.job }
            )
        }
}

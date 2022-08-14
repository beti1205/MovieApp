package com.example.movieplayer.feature.fetchcredits.domain

import com.example.movieplayer.common.AppConfig
import com.example.movieplayer.common.Result
import com.example.movieplayer.common.performRequest
import com.example.movieplayer.feature.fetchcredits.data.Cast
import com.example.movieplayer.feature.fetchcredits.data.Credits
import com.example.movieplayer.feature.fetchcredits.data.CreditsService
import com.example.movieplayer.feature.fetchcredits.data.Crew
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
        val result = performRequest { creditsService.getMovieCredits(id, appConfig.apiKey) }

        return when (result) {
            is Result.Success -> Result.Success(
                result.data.copy(
                    crew = result.data.crew
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
                        },
                    cast = result.data.cast
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
                )
            )
            is Result.Error -> result
        }
    }
}

package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.credits.data.CreditsService
import com.beti1205.movieapp.feature.credits.domain.FetchMovieCreditsUseCase
import com.beti1205.movieapp.feature.credits.domain.FetchMovieCreditsUseCaseImpl
import com.beti1205.movieapp.feature.credits.domain.FetchTVSeriesCreditsUseCase
import com.beti1205.movieapp.feature.credits.domain.FetchTVSeriesCreditsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface CreditsModule {

    companion object {
        @Provides
        fun provideCreditsService(retrofit: Retrofit): CreditsService = retrofit.create()
    }

    @Binds
    fun bindMovieCreditsUseCase(
        fetchMovieCreditsUseCaseImpl: FetchMovieCreditsUseCaseImpl
    ): FetchMovieCreditsUseCase

    @Binds
    fun bindTVSeriesCreditsUseCase(
        fetchTVSeriesCreditsUseCaseImpl: FetchTVSeriesCreditsUseCaseImpl
    ): FetchTVSeriesCreditsUseCase
}

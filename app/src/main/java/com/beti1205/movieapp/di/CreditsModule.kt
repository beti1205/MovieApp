package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.fetchcredits.data.CreditsService
import com.beti1205.movieapp.feature.fetchcredits.domain.FetchMovieCreditsUseCase
import com.beti1205.movieapp.feature.fetchcredits.domain.FetchMovieCreditsUseCaseImpl
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
    fun bindCreditsUseCase(
        fetchMovieCreditsUseCaseImpl: FetchMovieCreditsUseCaseImpl
    ): FetchMovieCreditsUseCase
}

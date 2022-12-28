package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.fetchmoviereviews.data.MovieReviewsService
import com.beti1205.movieapp.feature.fetchmoviereviews.domain.FetchMovieReviewsUseCase
import com.beti1205.movieapp.feature.fetchmoviereviews.domain.FetchMovieReviewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface MovieReviewsModule {

    companion object {
        @Provides
        fun provideMovieReviewsService(retrofit: Retrofit): MovieReviewsService = retrofit.create()
    }

    @Binds
    fun bindMovieReviewsUseCase(
        fetchMovieReviewsUseCaseImpl: FetchMovieReviewsUseCaseImpl
    ): FetchMovieReviewsUseCase
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.moviereviews.data.MovieReviewsService
import com.beti1205.movieapp.feature.moviereviews.domain.FetchMovieReviewsUseCase
import com.beti1205.movieapp.feature.moviereviews.domain.FetchMovieReviewsUseCaseImpl
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

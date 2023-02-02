/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.moviedetails.data.MovieDetailsService
import com.beti1205.movieapp.feature.moviedetails.domain.FetchMovieDetailsUseCase
import com.beti1205.movieapp.feature.moviedetails.domain.FetchMovieDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface MovieDetailsModule {

    companion object {
        @Provides
        fun provideMovieDetailsService(
            retrofit: Retrofit
        ): MovieDetailsService =
            retrofit.create()
    }

    @Binds
    fun bindFetchMovieDetailsUseCase(
        fetchMovieDetailsUseCaseImpl: FetchMovieDetailsUseCaseImpl
    ): FetchMovieDetailsUseCase
}

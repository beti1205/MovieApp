/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.combinedmoviedetails.FetchCombinedMovieDetailsUseCase
import com.beti1205.movieapp.feature.combinedmoviedetails.FetchCombinedMovieDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface CombinedMovieDetailsModule {

    @Binds
    fun bindCombinedMovieDetailsUseCase(
        fetchCombinedMovieDetailsUseCaseImpl: FetchCombinedMovieDetailsUseCaseImpl
    ): FetchCombinedMovieDetailsUseCase
}

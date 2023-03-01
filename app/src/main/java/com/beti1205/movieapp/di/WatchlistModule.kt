/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.watchlist.data.AddToWatchlistService
import com.beti1205.movieapp.feature.watchlist.domain.AddToWatchlistUseCase
import com.beti1205.movieapp.feature.watchlist.domain.AddToWatchlistUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface WatchlistModule {

    companion object {
        @Provides
        fun provideAddToWatchlistService(retrofit: Retrofit): AddToWatchlistService =
            retrofit.create()
    }

    @Binds
    fun bindAddToWatchlistUseCase(
        addToWatchlistUseCaseImpl: AddToWatchlistUseCaseImpl
    ): AddToWatchlistUseCase
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.token.data.RequestTokenService
import com.beti1205.movieapp.feature.token.domain.RequestTokenUseCase
import com.beti1205.movieapp.feature.token.domain.RequestTokenUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface TokenModule {

    companion object {
        @Provides
        fun provideRequestTokenService(retrofit: Retrofit): RequestTokenService = retrofit.create()
    }

    @Binds
    fun bindFetchRequestTokenUseCase(
        fetchRequestTokenUseCaseImpl: RequestTokenUseCaseImpl
    ): RequestTokenUseCase
}

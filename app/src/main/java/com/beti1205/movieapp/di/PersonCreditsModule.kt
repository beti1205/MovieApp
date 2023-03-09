/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.personcredits.domain.FetchPersonCreditsUseCase
import com.beti1205.movieapp.feature.personcredits.domain.FetchPersonCreditsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
interface PersonCreditsModule {

    @Binds
    fun bindPersonCreditsUseCase(
        fetchPersonCreditsUseCaseImpl: FetchPersonCreditsUseCaseImpl
    ): FetchPersonCreditsUseCase
}

/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.personmoviecredits.data.PersonMovieCreditsService
import com.beti1205.movieapp.feature.personmoviecredits.domain.FetchPersonMovieCreditsUseCase
import com.beti1205.movieapp.feature.personmoviecredits.domain.FetchPersonMovieCreditsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface PersonMovieCreditsModule {

    companion object {
        @Provides
        fun providePersonMovieCreditsService(
            retrofit: Retrofit
        ): PersonMovieCreditsService = retrofit.create()
    }

    @Binds
    fun bindFetchPersonMovieCreditsUseCase(
        fetchPersonMovieCreditsUseCaseImpl: FetchPersonMovieCreditsUseCaseImpl
    ): FetchPersonMovieCreditsUseCase
}

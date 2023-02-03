/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.tvseriesdetails.data.TVSeriesDetailsService
import com.beti1205.movieapp.feature.tvseriesdetails.domain.FetchTVSeriesDetailsUseCase
import com.beti1205.movieapp.feature.tvseriesdetails.domain.FetchTVSeriesDetailsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface TVSeriesDetailsModule {

    companion object {
        @Provides
        fun provideTVSeriesDetailsService(
            retrofit: Retrofit
        ): TVSeriesDetailsService =
            retrofit.create()
    }

    @Binds
    fun bindFetchTVSeriesDetailsUseCase(
        fetchTVSeriesDetailsUseCaseImpl: FetchTVSeriesDetailsUseCaseImpl
    ): FetchTVSeriesDetailsUseCase
}

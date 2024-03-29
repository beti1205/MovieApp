/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.tvseries.data.FavoriteTVSeriesService
import com.beti1205.movieapp.feature.tvseries.data.TVSeriesService
import com.beti1205.movieapp.feature.tvseries.data.TVSeriesWatchlistService
import com.beti1205.movieapp.feature.tvseries.domain.FetchFavoriteTVSeriesUseCase
import com.beti1205.movieapp.feature.tvseries.domain.FetchFavoriteTVSeriesUseCaseImpl
import com.beti1205.movieapp.feature.tvseries.domain.FetchTVSeriesUseCase
import com.beti1205.movieapp.feature.tvseries.domain.FetchTVSeriesUseCaseImpl
import com.beti1205.movieapp.feature.tvseries.domain.FetchTVSeriesWatchlistUseCase
import com.beti1205.movieapp.feature.tvseries.domain.FetchTVSeriesWatchlistUseCaseImpl
import com.beti1205.movieapp.feature.tvseries.domain.SearchTVSeriesUseCase
import com.beti1205.movieapp.feature.tvseries.domain.SearchTVSeriesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface TVSeriesModule {

    companion object {
        @Provides
        fun provideTVSeriesService(retrofit: Retrofit): TVSeriesService = retrofit.create()

        @Provides
        fun provideFavoriteTVSeriesService(
            retrofit: Retrofit
        ): FavoriteTVSeriesService = retrofit.create()

        @Provides
        fun provideTVSeriesWatchlistService(
            retrofit: Retrofit
        ): TVSeriesWatchlistService = retrofit.create()
    }

    @Binds
    fun bindFetchTVSeriesUseCase(
        fetchTVSeriesUseCaseImpl: FetchTVSeriesUseCaseImpl
    ): FetchTVSeriesUseCase

    @Binds
    fun bindSearchTVSeriesUseCase(
        searchTVSeriesUseCaseImpl: SearchTVSeriesUseCaseImpl
    ): SearchTVSeriesUseCase

    @Binds
    fun bindFetchFavoriteTVSeriesUseCase(
        fetchFavoriteTVSeriesUseCaseImpl: FetchFavoriteTVSeriesUseCaseImpl
    ): FetchFavoriteTVSeriesUseCase

    @Binds
    fun bindFetchTVSeriesWatchlistUseCase(
        fetchTVSeriesWatchlistUseCaseImpl: FetchTVSeriesWatchlistUseCaseImpl
    ): FetchTVSeriesWatchlistUseCase
}

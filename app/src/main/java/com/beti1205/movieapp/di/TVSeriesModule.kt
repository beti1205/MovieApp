package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.fetchtvseries.data.TVSeriesService
import com.beti1205.movieapp.feature.fetchtvseries.domain.FetchTVSeriesUseCase
import com.beti1205.movieapp.feature.fetchtvseries.domain.FetchTVSeriesUseCaseImpl
import com.beti1205.movieapp.feature.fetchtvseries.domain.SearchTVSeriesUseCase
import com.beti1205.movieapp.feature.fetchtvseries.domain.SearchTVSeriesUseCaseImpl
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
    }

    @Binds
    fun bindFetchTVSeriesUseCase(
        fetchTVSeriesUseCaseImpl: FetchTVSeriesUseCaseImpl
    ): FetchTVSeriesUseCase

    @Binds
    fun bindSearchTVSeriesUseCase(
        searchTVSeriesUseCaseImpl: SearchTVSeriesUseCaseImpl
    ): SearchTVSeriesUseCase
}

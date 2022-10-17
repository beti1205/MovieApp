package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.fetchtvseriesdetails.data.TVSeriesDetailsService
import com.beti1205.movieapp.feature.fetchtvseriesdetails.domain.FetchTVSeriesDetailsUseCase
import com.beti1205.movieapp.feature.fetchtvseriesdetails.domain.FetchTVSeriesDetailsUseCaseImpl
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

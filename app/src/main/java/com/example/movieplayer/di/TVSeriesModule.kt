package com.example.movieplayer.di

import com.example.movieplayer.common.AppConfig
import com.example.movieplayer.feature.fetchtvseries.data.TVSeriesService
import com.example.movieplayer.feature.fetchtvseries.domain.FetchTVSeriesUseCase
import com.example.movieplayer.feature.fetchtvseries.domain.FetchTVSeriesUseCaseImpl
import com.example.movieplayer.feature.fetchtvseries.domain.SearchTVSeriesUseCase
import com.example.movieplayer.feature.fetchtvseries.domain.SearchTVSeriesUseCaseImpl
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
abstract class TVSeriesModule {

    companion object {
        @Provides
        fun provideTVSeriesService(
            client: OkHttpClient,
            moshi: Moshi,
            appConfig: AppConfig
        ): TVSeriesService = Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(appConfig.baseUrl)
            .build()
            .create()
    }

    @Binds
    abstract fun bindFetchTVSeriesUseCase(
        fetchTVSeriesUseCaseImpl: FetchTVSeriesUseCaseImpl
    ): FetchTVSeriesUseCase

    @Binds
    abstract fun bindSearchTVSeriesUseCase(
        searchTVSeriesUseCaseImpl: SearchTVSeriesUseCaseImpl
    ): SearchTVSeriesUseCase
}

package com.example.movieplayer.di

import com.example.movieplayer.common.AppConfig
import com.example.movieplayer.feature.fetchtvseriesdetails.data.TVSeriesDetailsService
import com.example.movieplayer.feature.fetchtvseriesdetails.domain.FetchTVSeriesDetailsUseCase
import com.example.movieplayer.feature.fetchtvseriesdetails.domain.FetchTVSeriesDetailsUseCaseImpl
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
abstract class TVSeriesDetailsModule {

    companion object {
        @Provides
        fun provideTVSeriesDetailsService(
            client: OkHttpClient,
            moshi: Moshi,
            appConfig: AppConfig
        ): TVSeriesDetailsService = Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(appConfig.baseUrl)
            .build()
            .create()
    }

    @Binds
    abstract fun bindFetchTVSeriesDetailsUseCase(
        fetchTVSeriesDetailsUseCaseImpl: FetchTVSeriesDetailsUseCaseImpl
    ): FetchTVSeriesDetailsUseCase
}

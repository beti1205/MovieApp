package com.example.movieplayer.di

import com.example.movieplayer.common.AppConfig
import com.example.movieplayer.feature.fetchcredits.data.CreditsService
import com.example.movieplayer.feature.fetchcredits.domain.FetchMovieCreditsUseCase
import com.example.movieplayer.feature.fetchcredits.domain.FetchMovieCreditsUseCaseImpl
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
abstract class CreditsModule {

    companion object {
        @Provides
        fun provideCreditsService(
            client: OkHttpClient,
            moshi: Moshi,
            appConfig: AppConfig
        ): CreditsService = Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(appConfig.baseUrl)
            .build()
            .create()
    }

    @Binds
    abstract fun bindCreditsUseCase(
        fetchMovieCreditsUseCaseImpl: FetchMovieCreditsUseCaseImpl
    ): FetchMovieCreditsUseCase
}

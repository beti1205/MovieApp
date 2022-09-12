package com.beti1205.movieapp.di

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.feature.fetchcredits.data.CreditsService
import com.beti1205.movieapp.feature.fetchcredits.domain.FetchMovieCreditsUseCase
import com.beti1205.movieapp.feature.fetchcredits.domain.FetchMovieCreditsUseCaseImpl
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

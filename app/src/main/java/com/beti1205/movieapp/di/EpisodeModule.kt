package com.beti1205.movieapp.di

import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.feature.fetchtvepisodes.data.EpisodeService
import com.beti1205.movieapp.feature.fetchtvepisodes.domain.FetchEpisodesUseCase
import com.beti1205.movieapp.feature.fetchtvepisodes.domain.FetchEpisodesUseCaseImpl
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
abstract class EpisodeModule {

    companion object {
        @Provides
        fun provideEpisodeApiService(
            client: OkHttpClient,
            moshi: Moshi,
            appConfig: AppConfig
        ): EpisodeService = Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(appConfig.baseUrl)
            .build()
            .create()
    }

    @Binds
    abstract fun bindEpisodeUseCase(
        fetchEpisodesUseCaseImpl: FetchEpisodesUseCaseImpl
    ): FetchEpisodesUseCase
}

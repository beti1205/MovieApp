package com.example.movieplayer.di

import com.example.movieplayer.common.AppConfig
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

@Module
@InstallIn(SingletonComponent::class)
object CoreModule {

    @Provides
    fun provideAppConfig(): AppConfig {
        return AppConfig(
                baseUrl = "https://api.themoviedb.org/3/",
                apiKey = "c33ec9fdf85b0eb9fb900af22206b062"
        )
    }

    @Provides
    fun provideMoshi(): Moshi = Moshi.Builder()
            .build()

    @Provides
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder().addInterceptor(
                    interceptor
            ).build()
}
/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.di

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.security.crypto.MasterKey
import com.beti1205.movieapp.BuildConfig
import com.beti1205.movieapp.common.AppConfig
import com.beti1205.movieapp.common.AuthManager
import com.beti1205.movieapp.common.AuthManagerImpl
import com.beti1205.movieapp.common.EncryptedSharedPreferencesBuilder
import com.beti1205.movieapp.feature.transformavatarurl.TransformAvatarUrlUseCase
import com.beti1205.movieapp.feature.transformavatarurl.TransformAvatarUrlUseCaseImpl
import com.fredporciuncula.flow.preferences.FlowSharedPreferences
import com.squareup.moshi.Moshi
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
interface CoreModule {

    companion object {
        @Provides
        fun provideAppConfig(): AppConfig {
            return AppConfig(
                baseUrl = "https://api.themoviedb.org/3/",
                imageUrl = "https://image.tmdb.org/t/p/w500",
                apiKey = BuildConfig.API_KEY
            )
        }

        @Provides
        fun provideMoshi(): Moshi = Moshi.Builder()
            .build()

        @Provides
        fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor =
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }

        @Provides
        fun provideOkHttpClient(interceptor: HttpLoggingInterceptor): OkHttpClient =
            OkHttpClient.Builder().addInterceptor(
                interceptor
            ).build()

        @Provides
        fun provideRetrofit(
            client: OkHttpClient,
            moshi: Moshi,
            appConfig: AppConfig
        ): Retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .baseUrl(appConfig.baseUrl)
            .build()

        @Singleton
        @Provides
        fun provideMasterKey(@ApplicationContext appContext: Context): MasterKey {
            return MasterKey.Builder(appContext)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
        }

        @Singleton
        @Provides
        @Named("sharedPreferences")
        fun provideSharedPreferences(@ApplicationContext appContext: Context): SharedPreferences {
            return appContext.getSharedPreferences("app_preference", MODE_PRIVATE)
        }

        @Singleton
        @Provides
        @Named("encryptedSharedPreferences")
        fun provideEncryptedSharedPreferences(
            @ApplicationContext appContext: Context,
            masterKey: MasterKey
        ): SharedPreferences = EncryptedSharedPreferencesBuilder(
            appContext,
            masterKey
        ).build()

        @Singleton
        @Provides
        @Named("encryptedFlowSharedPreferences")
        fun provideEncryptedFlowSharedPreferences(
            @Named("encryptedSharedPreferences") sharedPreferences: SharedPreferences
        ): FlowSharedPreferences = FlowSharedPreferences(sharedPreferences)
    }

    @Singleton
    @Binds
    fun bindAuthManager(
        authManagerImpl: AuthManagerImpl
    ): AuthManager

    @Binds
    fun bindTransformAvatarUrl(
        transformAvatarUrlImpl: TransformAvatarUrlUseCaseImpl
    ): TransformAvatarUrlUseCase
}

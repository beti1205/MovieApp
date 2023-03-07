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
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit

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

        @OptIn(ExperimentalSerializationApi::class)
        @Provides
        fun provideKotlinSerialization(): Converter.Factory {
            val contentType = "application/json".toMediaType()
            val json = Json {
                explicitNulls = false
                ignoreUnknownKeys = true
                isLenient = true
            }
            return json.asConverterFactory(contentType)
        }

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
            converterFactory: Converter.Factory,
            appConfig: AppConfig
        ): Retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(converterFactory)
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

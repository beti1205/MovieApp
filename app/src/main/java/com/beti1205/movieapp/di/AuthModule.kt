/*
 * Copyright (c) 2023. Beata Bujalska<beta.bujalska@gmail.com>
 * All rights reserved.
 */

package com.beti1205.movieapp.di

import android.content.Context
import android.content.SharedPreferences
import androidx.security.crypto.MasterKey
import com.beti1205.movieapp.common.auth.AuthManager
import com.beti1205.movieapp.common.auth.AuthManagerImpl
import com.beti1205.movieapp.common.auth.EncryptedSharedPreferencesBuilder
import com.beti1205.movieapp.feature.session.data.DeleteSessionService
import com.beti1205.movieapp.feature.session.data.SessionService
import com.beti1205.movieapp.feature.session.domain.CreateSessionUseCase
import com.beti1205.movieapp.feature.session.domain.CreateSessionUseCaseImpl
import com.beti1205.movieapp.feature.session.domain.DeleteSessionUseCase
import com.beti1205.movieapp.feature.session.domain.DeleteSessionUseCaseImpl
import com.beti1205.movieapp.feature.token.data.RequestTokenService
import com.beti1205.movieapp.feature.token.domain.RequestTokenUseCase
import com.beti1205.movieapp.feature.token.domain.RequestTokenUseCaseImpl
import com.fredporciuncula.flow.preferences.FlowSharedPreferences
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(SingletonComponent::class)
interface AuthModule {

    companion object {
        @Provides
        fun provideRequestTokenService(retrofit: Retrofit): RequestTokenService = retrofit.create()

        @Provides
        fun provideSessionService(retrofit: Retrofit): SessionService = retrofit.create()

        @Provides
        fun provideDeleteSessionService(
            retrofit: Retrofit
        ): DeleteSessionService = retrofit.create()

        @Singleton
        @Provides
        fun provideMasterKey(@ApplicationContext appContext: Context): MasterKey {
            return MasterKey.Builder(appContext)
                .setKeyScheme(MasterKey.KeyScheme.AES256_GCM)
                .build()
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
    fun bindFetchRequestTokenUseCase(
        fetchRequestTokenUseCaseImpl: RequestTokenUseCaseImpl
    ): RequestTokenUseCase

    @Binds
    fun bindCreateSessionUseCase(
        createSessionUseCaseImpl: CreateSessionUseCaseImpl
    ): CreateSessionUseCase

    @Binds
    fun bindDeleteSessionUseCase(
        deleteSessionUseCaseImpl: DeleteSessionUseCaseImpl
    ): DeleteSessionUseCase
}

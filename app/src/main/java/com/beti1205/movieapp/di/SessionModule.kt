package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.createsession.data.SessionService
import com.beti1205.movieapp.feature.createsession.domain.CreateSessionUseCase
import com.beti1205.movieapp.feature.createsession.domain.CreateSessionUseCaseImpl
import com.beti1205.movieapp.feature.deletesession.data.DeleteSessionService
import com.beti1205.movieapp.feature.deletesession.domain.DeleteSessionUseCase
import com.beti1205.movieapp.feature.deletesession.domain.DeleteSessionUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface SessionModule {

    companion object {
        @Provides
        fun provideSessionService(retrofit: Retrofit): SessionService = retrofit.create()

        @Provides
        fun provideDeleteSessionService(
            retrofit: Retrofit
        ): DeleteSessionService = retrofit.create()
    }

    @Binds
    fun bindCreateSessionUseCase(
        createSessionUseCaseImpl: CreateSessionUseCaseImpl
    ): CreateSessionUseCase

    @Binds
    fun bindDeleteSessionUseCase(
        deleteSessionUseCaseImpl: DeleteSessionUseCaseImpl
    ): DeleteSessionUseCase
}

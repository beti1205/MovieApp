package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.createrequesttoken.data.RequestTokenService
import com.beti1205.movieapp.feature.createrequesttoken.domain.CreateRequestTokenUseCase
import com.beti1205.movieapp.feature.createrequesttoken.domain.CreateRequestTokenUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface TokenModule {

    companion object {
        @Provides
        fun provideRequestTokenService(retrofit: Retrofit): RequestTokenService = retrofit.create()
    }

    @Binds
    fun bindCreateRequestTokenUseCase(
        createRequestTokenUseCaseImpl: CreateRequestTokenUseCaseImpl
    ): CreateRequestTokenUseCase
}

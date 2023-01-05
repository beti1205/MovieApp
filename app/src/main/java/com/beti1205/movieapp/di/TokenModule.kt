package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.fetchrequesttoken.data.RequestTokenService
import com.beti1205.movieapp.feature.fetchrequesttoken.domain.FetchRequestTokenUseCase
import com.beti1205.movieapp.feature.fetchrequesttoken.domain.FetchRequestTokenUseCaseImpl
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
    fun bindFetchRequestTokenUseCase(
        fetchRequestTokenUseCaseImpl: FetchRequestTokenUseCaseImpl
    ): FetchRequestTokenUseCase
}

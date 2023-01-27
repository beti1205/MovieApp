package com.beti1205.movieapp.di

import com.beti1205.movieapp.feature.accountdetails.data.AccountDetailsService
import com.beti1205.movieapp.feature.accountdetails.domain.FetchAccountDetailsUseCase
import com.beti1205.movieapp.feature.accountdetails.domain.FetchAccountDetailsUseCaseImpl
import com.beti1205.movieapp.feature.accountstates.data.AccountStatesService
import com.beti1205.movieapp.feature.accountstates.domain.FetchMoviesAccountStatesUseCase
import com.beti1205.movieapp.feature.accountstates.domain.FetchMoviesAccountStatesUseCaseImpl
import com.beti1205.movieapp.feature.accountstates.domain.FetchTVAccountStatesUseCase
import com.beti1205.movieapp.feature.accountstates.domain.FetchTVAccountStatesUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import retrofit2.Retrofit
import retrofit2.create

@Module
@InstallIn(ViewModelComponent::class)
interface AccountModule {

    companion object {
        @Provides
        fun provideAccountDetailsService(retrofit: Retrofit): AccountDetailsService =
            retrofit.create()

        @Provides
        fun provideAccountStatesService(retrofit: Retrofit): AccountStatesService =
            retrofit.create()
    }

    @Binds
    fun bindFetchAccountDetailsUseCase(
        fetchAccountDetailsUseCaseImpl: FetchAccountDetailsUseCaseImpl
    ): FetchAccountDetailsUseCase

    @Binds
    fun bindFetchAccountStatesUseCase(
        fetchMoviesAccountStatesUseCaseImpl: FetchMoviesAccountStatesUseCaseImpl
    ): FetchMoviesAccountStatesUseCase

    @Binds
    fun bindFetchTVAccountStatesUseCase(
        fetchTVAccountStatesUseCaseImpl: FetchTVAccountStatesUseCaseImpl
    ): FetchTVAccountStatesUseCase
}
